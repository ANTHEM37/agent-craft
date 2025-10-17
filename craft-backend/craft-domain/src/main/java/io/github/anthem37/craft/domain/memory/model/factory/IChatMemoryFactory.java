package io.github.anthem37.craft.domain.memory.model.factory;

import dev.langchain4j.memory.ChatMemory;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;

/**
 * 聊天记忆工厂接口
 * 
 * @author hb28301
 * @since 2025/10/14 10:43:06
 */
public interface IChatMemoryFactory {

    /**
     * 创建聊天记忆
     *
     * @param memoryConfig 记忆配置
     * @param memoryId 记忆ID
     * @return 聊天记忆
     */
    ChatMemory createChatMemory(ChatMemoryConfig memoryConfig, Long memoryId);
}
