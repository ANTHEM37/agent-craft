package io.github.anthem37.craft.application.memory.cqrs.handler.command;

import io.github.anthem37.craft.application.memory.assembler.command.ChatMemoryConfigCommandAssembler;
import io.github.anthem37.craft.application.memory.dto.command.UpdateChatMemoryConfigCommand;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.repository.IChatMemoryConfigDomainRepository;
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
        // 转换为领域对象
        ChatMemoryConfig chatMemoryConfig = ChatMemoryConfigCommandAssembler.INSTANCE.toDomain(command);

        // 验证聚合根的业务不变性
        chatMemoryConfig.validateInvariants();

        // 更新聚合根
        ChatMemoryConfigDomainRepository.update(chatMemoryConfig);
        return true;
    }

    @Override
    public Class<UpdateChatMemoryConfigCommand> getSupportedCommandType() {

        return UpdateChatMemoryConfigCommand.class;
    }
}
