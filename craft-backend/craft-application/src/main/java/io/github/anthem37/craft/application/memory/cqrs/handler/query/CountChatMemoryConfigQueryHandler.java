package io.github.anthem37.craft.application.memory.cqrs.handler.query;

import io.github.anthem37.craft.application.memory.dto.query.CountChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.respository.IChatMemoryConfigRepository;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/11 17:15:10
 */
@Component
@RequiredArgsConstructor
public class CountChatMemoryConfigQueryHandler implements IQueryHandler<CountChatMemoryConfigQuery, Long> {

    private final IChatMemoryConfigRepository ChatMemoryConfigRepository;

    @Override
    public Long handle(CountChatMemoryConfigQuery query) {

        return ChatMemoryConfigRepository.countByConfigNameAndChatMemoryType(query.getConfigName(), query.getChatMemoryType());
    }

    @Override
    public Class<CountChatMemoryConfigQuery> getSupportedQueryType() {
        return CountChatMemoryConfigQuery.class;
    }
}
