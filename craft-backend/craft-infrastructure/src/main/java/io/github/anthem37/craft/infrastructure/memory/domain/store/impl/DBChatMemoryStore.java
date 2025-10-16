package io.github.anthem37.craft.infrastructure.memory.domain.store.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import io.github.anthem37.craft.domain.memory.model.value.ChatContent;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryPO;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 数据库存储
 *
 * @author hb28301
 * @date 2025/10/14 14:44:20
 */
@RequiredArgsConstructor
public class DBChatMemoryStore implements ChatMemoryStore {

    private final IChatMemoryMapper chatMessageMapper;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        Optional<ChatMemoryPO> chatMemoryPOOptional = new LambdaQueryChainWrapper<>(chatMessageMapper)
                .eq(ChatMemoryPO::getId, memoryId)
                .oneOpt();
        if (chatMemoryPOOptional.isEmpty()) {

            return new ArrayList<>();
        }

        return chatMemoryPOOptional.get().getContent().toLangChain4jChatMessages();
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        new LambdaUpdateChainWrapper<>(chatMessageMapper)
                .eq(ChatMemoryPO::getId, memoryId)
                .set(ChatMemoryPO::getContent, new ChatContent().setMessages(messages.stream().map(message -> new ChatContent.ChatMessageData(message.type(), JSONUtil.toJsonStr(message))).toList()))
                .update();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMessages(Object memoryId) {
        new LambdaUpdateChainWrapper<>(chatMessageMapper)
                .eq(ChatMemoryPO::getId, memoryId)
                .remove();
    }

}
