package io.github.anthem37.craft.application.memory.service.impl.chat;

import dev.langchain4j.memory.ChatMemory;
import io.github.anthem37.craft.application.memory.dto.command.CreateChatMemoryCommand;
import io.github.anthem37.craft.application.memory.service.IChatMemoryService;
import io.github.anthem37.easy.ddd.application.AbstractApplicationService;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandBus;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryBus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 聊天记忆创建服务实现
 * 负责协调ChatMemory的创建和绑定流程
 *
 * @author hb28301
 * @since 2025/01/15
 */
@Service
public class ChatMemoryService extends AbstractApplicationService implements IChatMemoryService {

    public ChatMemoryService(ICommandBus commandBus, IQueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ChatMemory createChatMemory(CreateChatMemoryCommand command) {

        return sendCommand(command);
    }

}