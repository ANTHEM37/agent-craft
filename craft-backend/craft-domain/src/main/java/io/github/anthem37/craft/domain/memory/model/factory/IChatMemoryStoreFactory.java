package io.github.anthem37.craft.domain.memory.model.factory;

import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryStoreType;

/**
 * @author hb28301
 * @date 2025/10/14 10:43:06
 */
public interface IChatMemoryStoreFactory {

    /**
     * 创建聊天记忆存储
     *
     * @param chatMemoryStoreType 聊天记忆存储类型
     * @return 聊天记忆存储
     */
    ChatMemoryStore createChatMemoryStore(ChatMemoryStoreType chatMemoryStoreType);
}
