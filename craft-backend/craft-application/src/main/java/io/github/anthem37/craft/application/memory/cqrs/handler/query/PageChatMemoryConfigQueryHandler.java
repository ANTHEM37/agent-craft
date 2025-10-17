package io.github.anthem37.craft.application.memory.cqrs.handler.query;

import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.application.memory.dto.query.PageChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.repository.IChatMemoryConfigRepository;
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

    private final IChatMemoryConfigRepository ChatMemoryConfigRepository;

    @Override
    public PageDTO<ChatMemoryConfigDTO> handle(PageChatMemoryConfigQuery query) {

        return ChatMemoryConfigRepository.pageByConfigNameAndChatMemoryType(query.getCurrent(), query.getSize(), query.getConfigName(), query.getChatMemoryType());
    }

    @Override
    public Class<PageChatMemoryConfigQuery> getSupportedQueryType() {
        return PageChatMemoryConfigQuery.class;
    }
}
