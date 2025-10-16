package io.github.anthem37.craft.application.memory.cqrs.handler.command;

import io.github.anthem37.craft.application.memory.converter.IChatMemoryConfigCommandConverter;
import io.github.anthem37.craft.application.memory.dto.command.UpdateChatMemoryConfigCommand;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.respository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/11 17:14:03
 */
@Component
@RequiredArgsConstructor
public class UpdateChatMemoryConfigCommandHandler implements ICommandHandler<UpdateChatMemoryConfigCommand, Boolean> {

    private final IChatMemoryConfigDomainRepository ChatMemoryConfigDomainRepository;

    @Override
    public Boolean handle(UpdateChatMemoryConfigCommand command) {
        ChatMemoryConfig ChatMemoryConfig = IChatMemoryConfigCommandConverter.INSTANCE.toDomain(command);
        ChatMemoryConfigDomainRepository.update(ChatMemoryConfig);
        return true;
    }

    @Override
    public Class<UpdateChatMemoryConfigCommand> getSupportedCommandType() {

        return UpdateChatMemoryConfigCommand.class;
    }
}
