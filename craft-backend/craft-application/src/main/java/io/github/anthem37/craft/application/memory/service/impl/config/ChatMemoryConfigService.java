package io.github.anthem37.craft.application.memory.service.impl.config;

import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.memory.cqrs.command.CreateChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.cqrs.command.DeleteChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.cqrs.command.UpdateChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.cqrs.query.CountChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.cqrs.query.FindOneChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.cqrs.query.ListChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.cqrs.query.PageChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.application.memory.service.IChatMemoryConfigService;
import io.github.anthem37.easy.ddd.application.AbstractApplicationService;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandBus;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryBus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 记忆配置服务实现类
 *
 * @author hb28301
 * @since 2025/10/11 16:58:26
 */
@Service
public class ChatMemoryConfigService extends AbstractApplicationService implements IChatMemoryConfigService {


    public ChatMemoryConfigService(ICommandBus commandBus, IQueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean create(CreateChatMemoryConfigCommand command) {
        return sendCommand(command);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean update(UpdateChatMemoryConfigCommand command) {
        return sendCommand(command);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delete(DeleteChatMemoryConfigCommand command) {
        return sendCommand(command);
    }

    @Override
    public ChatMemoryConfigDTO findOne(FindOneChatMemoryConfigQuery query) {
        return sendQuery(query);
    }

    @Override
    public List<ChatMemoryConfigDTO> list(ListChatMemoryConfigQuery query) {
        return sendQuery(query);
    }

    @Override
    public Long count(CountChatMemoryConfigQuery query) {
        return sendQuery(query);
    }

    @Override
    public PageDTO<ChatMemoryConfigDTO> page(PageChatMemoryConfigQuery query) {
        return sendQuery(query);
    }
}
