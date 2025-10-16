package io.github.anthem37.craft.infrastructure.memory.domain.factory.impl;

import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import io.github.anthem37.craft.domain.memory.model.factory.IChatMemoryStoreFactory;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryStoreType;
import io.github.anthem37.craft.infrastructure.memory.domain.store.impl.DBChatMemoryStore;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/16 13:45:00
 */
@Component
@RequiredArgsConstructor
public class ChatMemoryStoreFactory implements IChatMemoryStoreFactory {

    private final IChatMemoryMapper chatMessageMapper;

    @Override
    public ChatMemoryStore createChatMemoryStore(ChatMemoryStoreType chatMemoryStoreType) {

        return switch (chatMemoryStoreType) {
            case IN_MEMORY -> new InMemoryChatMemoryStore();
            case IN_DB -> new DBChatMemoryStore(chatMessageMapper);
        };
    }

}
