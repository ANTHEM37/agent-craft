package io.github.anthem37.craft.domain.memory.model.factory;

import dev.langchain4j.memory.ChatMemory;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;

/**
 * @author hb28301
 * @date 2025/10/14 10:43:06
 */
public interface IChatMemoryFactory {

    /**
     * 创建聊天记忆
     *
     * @param memoryConfig 记忆配置
     * @return 聊天记忆
     */
    ChatMemory createChatMemory(ChatMemoryConfig memoryConfig);
}
