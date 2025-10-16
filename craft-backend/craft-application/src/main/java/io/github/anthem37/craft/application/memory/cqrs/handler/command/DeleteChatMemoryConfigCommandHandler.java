package io.github.anthem37.craft.application.memory.cqrs.handler.command;

import io.github.anthem37.craft.application.memory.dto.command.DeleteChatMemoryConfigCommand;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.respository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 删除聊天记忆配置命令处理程序
 *
 * @author hb28301
 * @since 2025/10/11 17:13:40
 */
@Component
@RequiredArgsConstructor
public class DeleteChatMemoryConfigCommandHandler implements ICommandHandler<DeleteChatMemoryConfigCommand, Boolean> {

    private final IChatMemoryConfigDomainRepository ChatMemoryConfigDomainRepository;

    @Override
    public Boolean handle(DeleteChatMemoryConfigCommand command) {
        Optional<ChatMemoryConfig> ChatMemoryConfigOptional = ChatMemoryConfigDomainRepository.findById(command.getId());
        if (ChatMemoryConfigOptional.isEmpty()) {
            return true;
        }
        ChatMemoryConfigDomainRepository.remove(ChatMemoryConfigOptional.get());
        return true;
    }

    @Override
    public Class<DeleteChatMemoryConfigCommand> getSupportedCommandType() {

        return DeleteChatMemoryConfigCommand.class;
    }
}
