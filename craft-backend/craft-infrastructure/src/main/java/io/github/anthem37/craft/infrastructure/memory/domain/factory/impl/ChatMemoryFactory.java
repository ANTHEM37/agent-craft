package io.github.anthem37.craft.infrastructure.memory.domain.factory.impl;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.model.factory.IChatMemoryFactory;
import io.github.anthem37.craft.domain.memory.model.factory.IChatMemoryStoreFactory;
import io.github.anthem37.craft.domain.memory.model.factory.ITokenCountEstimatorFactory;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryConfigParams;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryStoreType;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @date 2025/10/14 13:35:43
 */
@Component
@RequiredArgsConstructor
public class ChatMemoryFactory implements IChatMemoryFactory {

    private final IChatMemoryStoreFactory chatMemoryStoreFactory;
    private final ITokenCountEstimatorFactory tokenCountEstimatorFactory;

    @Override
    public ChatMemory createChatMemory(ChatMemoryConfig memoryConfig) {
        ChatMemoryType chatMemoryType = memoryConfig.getChatMemoryType();
        ChatMemoryConfigParams params = memoryConfig.getParams();
        ChatMemoryStoreType chatMemoryStoreType = params.getChatMemoryStoreType();
        ChatMemoryStore chatMemoryStore = chatMemoryStoreFactory.createChatMemoryStore(chatMemoryStoreType);
        switch (chatMemoryType) {
            case MESSAGE_WINDOW:
                ChatMemoryConfigParams.MessageWindowParams messageWindowParams = memoryConfig.parseParams(ChatMemoryConfigParams.MessageWindowParams.class);
                Integer maxMessages = messageWindowParams.getMaxMessages();

                return MessageWindowChatMemory.builder().maxMessages(maxMessages).chatMemoryStore(chatMemoryStore).build();
            case TOKEN_WINDOW:
                ChatMemoryConfigParams.TokenWindowParams tokenWindowParams = memoryConfig.parseParams(ChatMemoryConfigParams.TokenWindowParams.class);
                Integer maxTokens = tokenWindowParams.getMaxTokens();
                Long llmConfigId = tokenWindowParams.getLlmConfigId();

                return TokenWindowChatMemory.builder().maxTokens(maxTokens, tokenCountEstimatorFactory.createTokenCountEstimator(llmConfigId)).chatMemoryStore(chatMemoryStore).build();
            default:
                throw new IllegalArgumentException("Unknown chat memory type: " + chatMemoryType);
        }

    }

}
