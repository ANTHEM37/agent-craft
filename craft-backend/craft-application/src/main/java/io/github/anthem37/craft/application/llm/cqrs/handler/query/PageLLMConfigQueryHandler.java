package io.github.anthem37.craft.application.llm.cqrs.handler.query;

import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.application.llm.dto.query.PageLLMConfigQuery;
import io.github.anthem37.craft.application.llm.repository.ILLMConfigRepository;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @date 2025/10/11 17:16:04
 */
@Component
@RequiredArgsConstructor
public class PageLLMConfigQueryHandler implements IQueryHandler<PageLLMConfigQuery, PageDTO<LLMConfigDTO>> {

    private final ILLMConfigRepository llmConfigRepository;

    @Override
    public PageDTO<LLMConfigDTO> handle(PageLLMConfigQuery query) {

        return llmConfigRepository.pageByModelName(query.getCurrent(), query.getSize(), query.getModelName());
    }

    @Override
    public Class<PageLLMConfigQuery> getSupportedQueryType() {
        return PageLLMConfigQuery.class;
    }
}
