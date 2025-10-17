package io.github.anthem37.craft.application.llm.cqrs.handler.query;

import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.application.llm.dto.query.ListLLMConfigQuery;
import io.github.anthem37.craft.application.llm.repository.ILLMConfigQueryRepository;
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
public class ListLLMConfigQueryHandler implements IQueryHandler<ListLLMConfigQuery, List<LLMConfigDTO>> {

    private final ILLMConfigQueryRepository llmConfigQueryRepository;

    @Override
    public List<LLMConfigDTO> handle(ListLLMConfigQuery query) {

        return llmConfigQueryRepository.listByModelNameAndConfigNameAndProvider(query.getModelName(), query.getConfigName(), query.getProvider());
    }

    @Override
    public Class<ListLLMConfigQuery> getSupportedQueryType() {
        return ListLLMConfigQuery.class;
    }
}
