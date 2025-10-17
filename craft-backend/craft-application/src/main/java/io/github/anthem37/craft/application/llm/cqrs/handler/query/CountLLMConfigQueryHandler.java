package io.github.anthem37.craft.application.llm.cqrs.handler.query;

import io.github.anthem37.craft.application.llm.dto.query.CountLLMConfigQuery;
import io.github.anthem37.craft.application.llm.repository.ILLMConfigQueryRepository;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/11 17:15:10
 */
@Component
@RequiredArgsConstructor
public class CountLLMConfigQueryHandler implements IQueryHandler<CountLLMConfigQuery, Long> {

    private final ILLMConfigQueryRepository llmConfigQueryRepository;

    @Override
    public Long handle(CountLLMConfigQuery query) {

        return llmConfigQueryRepository.countByModelNameAndConfigNameAndProvider(query.getModelName(), query.getConfigName(), query.getProvider());
    }

    @Override
    public Class<CountLLMConfigQuery> getSupportedQueryType() {
        return CountLLMConfigQuery.class;
    }
}
