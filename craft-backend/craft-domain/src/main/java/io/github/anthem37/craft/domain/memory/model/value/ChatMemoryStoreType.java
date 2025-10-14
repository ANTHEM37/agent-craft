package io.github.anthem37.craft.domain.memory.model.value;

/**
 * 聊天记忆存储类型
 *
 * @author hb28301
 * @date 2025/10/14 10:49:25
 */
public enum ChatMemoryStoreType {

    /**
     * 内存聊天记忆存储 {@link dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore}
     */
    IN_MEMORY,

    /**
     * MySQL 聊天记忆存储
     */
    IN_MYSQL,

    /**
     * Redis 聊天记忆存储
     */
    IN_REDIS,
}
