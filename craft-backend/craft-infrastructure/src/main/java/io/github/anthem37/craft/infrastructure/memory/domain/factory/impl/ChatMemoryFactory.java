package io.github.anthem37.craft.infrastructure.memory.domain.factory.impl;

import cn.hutool.core.util.IdUtil;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import io.github.anthem37.craft.domain.llm.model.factory.IChatModelFactory;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.model.factory.IChatMemoryFactory;
import io.github.anthem37.craft.domain.memory.model.factory.IChatMemoryStoreFactory;
import io.github.anthem37.craft.domain.memory.model.factory.ITokenCountEstimatorFactory;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryConfigParams;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryStoreType;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import io.github.anthem37.craft.infrastructure.memory.domain.memory.impl.SummaryWindowChatMemory;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigRefMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigRefPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hb28301
 * @since 2025/10/14 13:35:43
 */
@Component
@RequiredArgsConstructor
public class ChatMemoryFactory implements IChatMemoryFactory {

    private final IChatMemoryStoreFactory chatMemoryStoreFactory;
    private final ITokenCountEstimatorFactory tokenCountEstimatorFactory;
    private final IChatModelFactory chatModelFactory;

    private final IChatMemoryConfigRefMapper chatMemoryConfigRefMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ChatMemory createChatMemory(ChatMemoryConfig memoryConfig) {
        long memoryId = IdUtil.getSnowflake().nextId();
        chatMemoryConfigRefMapper.insert(new ChatMemoryConfigRefPO().setMemoryId(memoryId).setConfigId(memoryConfig.getId()));
        ChatMemoryType chatMemoryType = memoryConfig.getChatMemoryType();
        ChatMemoryConfigParams params = memoryConfig.getParams();
        ChatMemoryStoreType chatMemoryStoreType = params.getChatMemoryStoreType();
        ChatMemoryStore chatMemoryStore = chatMemoryStoreFactory.createChatMemoryStore(chatMemoryStoreType);
        switch (chatMemoryType) {
            case MESSAGE_WINDOW: {
                ChatMemoryConfigParams.MessageWindowParams messageWindowParams = memoryConfig.parseParams(ChatMemoryConfigParams.MessageWindowParams.class);
                Integer maxMessages = messageWindowParams.getMaxMessages();

                return MessageWindowChatMemory.builder().id(memoryId).maxMessages(maxMessages).chatMemoryStore(chatMemoryStore).build();
            }

            case TOKEN_WINDOW: {
                ChatMemoryConfigParams.TokenWindowParams tokenWindowParams = memoryConfig.parseParams(ChatMemoryConfigParams.TokenWindowParams.class);
                Integer maxTokens = tokenWindowParams.getMaxTokens();
                Long llmConfigId = tokenWindowParams.getLlmConfigId();

                return TokenWindowChatMemory.builder().id(memoryId).maxTokens(maxTokens, tokenCountEstimatorFactory.createTokenCountEstimator(llmConfigId)).chatMemoryStore(chatMemoryStore).build();
            }
            case SUMMARY_WINDOW: {
                ChatMemoryConfigParams.SummaryWindowParams summaryWindowParams = memoryConfig.parseParams(ChatMemoryConfigParams.SummaryWindowParams.class);
                Integer maxMessages = summaryWindowParams.getMaxMessages();
                Long llmConfigId = summaryWindowParams.getLlmConfigId();

                return SummaryWindowChatMemory.builder().id(memoryId).maxMessages(maxMessages).chatModel(chatModelFactory.createChatModel(llmConfigId)).chatMemoryStore(chatMemoryStore).build();
            }
            default: {
                throw new IllegalArgumentException("未知聊天记忆类型: " + chatMemoryType);
            }
        }
    }

}
