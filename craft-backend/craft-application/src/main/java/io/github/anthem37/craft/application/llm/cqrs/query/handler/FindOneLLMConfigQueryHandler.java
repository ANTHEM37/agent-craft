package io.github.anthem37.craft.application.llm.cqrs.query.handler;

import io.github.anthem37.craft.application.llm.cqrs.query.FindOneLLMConfigQuery;
import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.application.llm.repository.ILLMConfigQueryRepository;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/11 17:15:21
 */
@Component
@RequiredArgsConstructor
public class FindOneLLMConfigQueryHandler implements IQueryHandler<FindOneLLMConfigQuery, LLMConfigDTO> {

    private final ILLMConfigQueryRepository llmConfigQueryRepository;

    @Override
    public LLMConfigDTO handle(FindOneLLMConfigQuery query) {

        return llmConfigQueryRepository.findById(query.getId());
    }

    @Override
    public Class<FindOneLLMConfigQuery> getSupportedQueryType() {
        return FindOneLLMConfigQuery.class;
    }
}
