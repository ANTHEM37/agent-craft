package io.github.anthem37.craft.infrastructure.memory.domain.store.impl;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import io.github.anthem37.craft.infrastructure.memory.myabtis.Mapper.IChatMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hb28301
 * @date 2025/10/14 14:44:20
 */
@Component
@RequiredArgsConstructor
public class MysqlChatMemoryStore implements ChatMemoryStore {

    private final IChatMessageMapper chatMessageMapper;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
      
        return List.of();
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {

    }

    @Override
    public void deleteMessages(Object memoryId) {

    }

}
