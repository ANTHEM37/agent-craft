package io.github.anthem37.craft.application.memory.cqrs.command.handler;

import io.github.anthem37.craft.application.memory.assembler.command.ChatMemoryConfigCommandAssembler;
import io.github.anthem37.craft.application.memory.cqrs.command.CreateChatMemoryConfigCommand;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.repository.IChatMemoryConfigDomainRepository;
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
        // 转换为领域对象
        ChatMemoryConfig chatMemoryConfig = ChatMemoryConfigCommandAssembler.INSTANCE.toDomain(command);

        // 验证聚合根的业务不变性
        chatMemoryConfig.validateInvariants();

        // 保存聚合根
        ChatMemoryConfigDomainRepository.save(chatMemoryConfig);
        return true;
    }

    @Override
    public Class<CreateChatMemoryConfigCommand> getSupportedCommandType() {

        return CreateChatMemoryConfigCommand.class;
    }
}
