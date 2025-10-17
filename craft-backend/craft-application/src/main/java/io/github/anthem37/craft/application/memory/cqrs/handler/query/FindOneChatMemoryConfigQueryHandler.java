package io.github.anthem37.craft.application.memory.cqrs.handler.query;

import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.application.memory.dto.query.FindOneChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.repository.IChatMemoryConfigRepository;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/11 17:15:21
 */
@Component
@RequiredArgsConstructor
public class FindOneChatMemoryConfigQueryHandler implements IQueryHandler<FindOneChatMemoryConfigQuery, ChatMemoryConfigDTO> {

    private final IChatMemoryConfigRepository chatMemoryConfigRepository;

    @Override
    public ChatMemoryConfigDTO handle(FindOneChatMemoryConfigQuery query) {

        return chatMemoryConfigRepository.findById(query.getId());
    }

    @Override
    public Class<FindOneChatMemoryConfigQuery> getSupportedQueryType() {
        return FindOneChatMemoryConfigQuery.class;
    }
}
