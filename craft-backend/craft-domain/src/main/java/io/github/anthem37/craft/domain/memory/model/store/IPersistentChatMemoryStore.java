package io.github.anthem37.craft.domain.memory.model.store;

import dev.langchain4j.store.memory.chat.ChatMemoryStore;

/**
 * 持久化存储
 *
 * @author hb28301
 * @date 2025/10/15 17:24:31
 */
public interface IPersistentChatMemoryStore extends ChatMemoryStore {

    /**
     * 持久化
     *
     * @param memoryId 记忆ID
     */
    void persistent(Object memoryId);
}
