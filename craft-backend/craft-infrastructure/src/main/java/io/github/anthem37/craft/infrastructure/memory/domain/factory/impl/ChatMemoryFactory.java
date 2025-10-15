package io.github.anthem37.craft.infrastructure.memory.domain.factory.impl;

import dev.langchain4j.memory.ChatMemory;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.model.factory.IChatMemoryFactory;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @date 2025/10/14 13:35:43
 */
@Component
public class ChatMemoryFactory implements IChatMemoryFactory {

    @Override
    public ChatMemory createChatMemory(ChatMemoryConfig memoryConfig) {
        return null;
    }

}
