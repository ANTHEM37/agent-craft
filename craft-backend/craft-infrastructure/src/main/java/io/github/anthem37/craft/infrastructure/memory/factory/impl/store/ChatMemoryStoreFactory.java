package io.github.anthem37.craft.infrastructure.memory.factory.impl.store;

import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import io.github.anthem37.craft.domain.memory.model.factory.IChatMemoryStoreFactory;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryStoreType;
import io.github.anthem37.craft.infrastructure.memory.store.impl.db.DBChatMemoryStore;
import io.github.anthem37.craft.infrastructure.memory.store.impl.redis.RedisChatMemoryStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/16 13:45:00
 */
@Component
@RequiredArgsConstructor
public class ChatMemoryStoreFactory implements IChatMemoryStoreFactory {

    private final DBChatMemoryStore dbChatMemoryStore;
    private final RedisChatMemoryStore redisChatMemoryStore;

    @Override
    public ChatMemoryStore createChatMemoryStore(ChatMemoryStoreType chatMemoryStoreType) {

        return switch (chatMemoryStoreType) {
            case IN_DB -> dbChatMemoryStore;
            case IN_REDIS -> redisChatMemoryStore;
        };
    }

}
