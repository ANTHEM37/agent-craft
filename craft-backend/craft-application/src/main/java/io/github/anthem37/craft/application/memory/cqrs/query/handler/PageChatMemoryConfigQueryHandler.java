package io.github.anthem37.craft.application.memory.cqrs.query.handler;

import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.memory.cqrs.query.PageChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.application.memory.repository.IChatMemoryConfigQueryRepository;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/11 17:16:04
 */
@Component
@RequiredArgsConstructor
public class PageChatMemoryConfigQueryHandler implements IQueryHandler<PageChatMemoryConfigQuery, PageDTO<ChatMemoryConfigDTO>> {

    private final IChatMemoryConfigQueryRepository chatMemoryConfigQueryRepository;

    @Override
    public PageDTO<ChatMemoryConfigDTO> handle(PageChatMemoryConfigQuery query) {

        return chatMemoryConfigQueryRepository.pageByConfigNameAndChatMemoryType(query.getCurrent(), query.getSize(), query.getConfigName(), query.getChatMemoryType());
    }

    @Override
    public Class<PageChatMemoryConfigQuery> getSupportedQueryType() {
        return PageChatMemoryConfigQuery.class;
    }
}
