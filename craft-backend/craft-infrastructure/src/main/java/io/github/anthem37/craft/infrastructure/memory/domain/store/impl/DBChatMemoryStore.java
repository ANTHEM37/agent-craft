package io.github.anthem37.craft.infrastructure.memory.domain.store.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageType;
import io.github.anthem37.craft.domain.memory.model.store.IPersistentChatMemoryStore;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMessageMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMessagePO;
import io.github.anthem37.craft.domain.memory.model.value.ChatMessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库存储
 *
 * @author hb28301
 * @date 2025/10/14 14:44:20
 */
@RequiredArgsConstructor
public class DBChatMemoryStore implements IPersistentChatMemoryStore {

    private final Map<Object, List<ChatMessage>> memoryIdMessagesMap = new ConcurrentHashMap<>(16);

    private final IChatMessageMapper chatMessageMapper;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        List<ChatMessage> messages = memoryIdMessagesMap.getOrDefault(memoryId, new ArrayList<>());
        List<ChatMessagePO> chatMessagePOS = new LambdaQueryChainWrapper<>(chatMessageMapper).eq(ChatMessagePO::getMemoryId, memoryId).eq(ChatMessagePO::getStatus, ChatMessageStatus.ACTIVE).list();
        if (CollectionUtil.isNotEmpty(chatMessagePOS)) {
            for (ChatMessagePO chatMessagePO : chatMessagePOS) {
                ChatMessageType chatMessageType = chatMessagePO.getType();
                ChatMessage message = JSONUtil.toBean(chatMessagePO.getContent(), chatMessageType.messageClass());
                messages.add(message);
            }
        }
        memoryIdMessagesMap.put(memoryId, messages);

        return messages;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        memoryIdMessagesMap.put(memoryId, messages);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMessages(Object memoryId) {
        memoryIdMessagesMap.remove(memoryId);
        new LambdaUpdateChainWrapper<>(chatMessageMapper)
                .eq(ChatMessagePO::getMemoryId, memoryId)
                .remove();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void persistent(Object memoryId) {
        List<ChatMessage> messages = memoryIdMessagesMap.get(memoryId);
        if (CollectionUtil.isNotEmpty(messages)) {
            for (ChatMessage message : messages) {
                ChatMessagePO chatMessagePO = new ChatMessagePO();
                chatMessagePO.setMemoryId(Long.parseLong(memoryId.toString()));
                chatMessagePO.setType(message.type());
                chatMessagePO.setContent(JSONUtil.toJsonStr(message));
                chatMessagePO.setStatus(ChatMessageStatus.ACTIVE);
                chatMessageMapper.insert(chatMessagePO);
            }
        }
    }

}
