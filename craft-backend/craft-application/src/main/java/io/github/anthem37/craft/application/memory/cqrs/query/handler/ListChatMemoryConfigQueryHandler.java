package io.github.anthem37.craft.application.memory.cqrs.query.handler;

import io.github.anthem37.craft.application.memory.cqrs.query.ListChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.application.memory.repository.IChatMemoryConfigQueryRepository;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hb28301
 * @since 2025/10/11 17:16:04
 */
@Component
@RequiredArgsConstructor
public class ListChatMemoryConfigQueryHandler implements IQueryHandler<ListChatMemoryConfigQuery, List<ChatMemoryConfigDTO>> {

    private final IChatMemoryConfigQueryRepository chatMemoryConfigQueryRepository;

    @Override
    public List<ChatMemoryConfigDTO> handle(ListChatMemoryConfigQuery query) {

        return chatMemoryConfigQueryRepository.listByConfigNameAndChatMemoryType(query.getConfigName(), query.getChatMemoryType());
    }

    @Override
    public Class<ListChatMemoryConfigQuery> getSupportedQueryType() {
        return ListChatMemoryConfigQuery.class;
    }
}
