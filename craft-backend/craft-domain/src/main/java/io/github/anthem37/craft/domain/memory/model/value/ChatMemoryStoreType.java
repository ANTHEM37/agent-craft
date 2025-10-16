package io.github.anthem37.craft.domain.memory.model.value;

import io.github.anthem37.easy.ddd.domain.model.IValueObject;

/**
 * 聊天记忆存储类型
 *
 * @author hb28301
 * @since 2025/10/14 10:49:25
 */
public enum ChatMemoryStoreType implements IValueObject {

    /**
     * 内存聊天记忆存储 {@link dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore}
     */
    IN_MEMORY,

    /**
     * 数据库存储聊天记忆
     */
    IN_DB,
}
