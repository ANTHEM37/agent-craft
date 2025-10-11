package io.github.anthem37.craft.application.llm.cqrs.handler.query;

import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.application.llm.dto.query.ListLLMConfigQuery;
import io.github.anthem37.craft.application.llm.repository.ILLMConfigRepository;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hb28301
 * @date 2025/10/11 17:16:04
 */
@Component
@RequiredArgsConstructor
public class ListLLMConfigQueryHandler implements IQueryHandler<ListLLMConfigQuery, List<LLMConfigDTO>> {

    private final ILLMConfigRepository llmConfigRepository;

    @Override
    public List<LLMConfigDTO> handle(ListLLMConfigQuery query) {

        return llmConfigRepository.listByModelName(query.getModelName());
    }

    @Override
    public Class<ListLLMConfigQuery> getSupportedQueryType() {
        return ListLLMConfigQuery.class;
    }
}
