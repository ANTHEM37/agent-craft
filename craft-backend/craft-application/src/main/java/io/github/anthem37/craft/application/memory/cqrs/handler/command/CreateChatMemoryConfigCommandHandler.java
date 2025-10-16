package io.github.anthem37.craft.application.memory.cqrs.handler.command;

import io.github.anthem37.craft.application.memory.converter.IChatMemoryConfigCommandConverter;
import io.github.anthem37.craft.application.memory.dto.command.CreateChatMemoryConfigCommand;
import io.github.anthem37.craft.domain.memory.respository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 创建聊天记忆配置命令处理程序
 *
 * @author hb28301
 * @since 2025/10/11 17:13:05
 */
@Component
@RequiredArgsConstructor
public class CreateChatMemoryConfigCommandHandler implements ICommandHandler<CreateChatMemoryConfigCommand, Boolean> {

    private final IChatMemoryConfigDomainRepository ChatMemoryConfigDomainRepository;

    @Override
    public Boolean handle(CreateChatMemoryConfigCommand command) {
        ChatMemoryConfigDomainRepository.save(IChatMemoryConfigCommandConverter.INSTANCE.toDomain(command));
        return true;
    }

    @Override
    public Class<CreateChatMemoryConfigCommand> getSupportedCommandType() {

        return CreateChatMemoryConfigCommand.class;
    }
}
