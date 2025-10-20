package io.github.anthem37.craft.application.memory.cqrs.command.handler;

import io.github.anthem37.craft.application.memory.cqrs.command.DeleteChatMemoryConfigCommand;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.repository.IChatMemoryConfigDomainRepository;
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
        // 查找聚合根
        Optional<ChatMemoryConfig> chatMemoryConfigOptional = ChatMemoryConfigDomainRepository.findById(command.getId());
        if (chatMemoryConfigOptional.isEmpty()) {
            return true;
        }

        ChatMemoryConfig chatMemoryConfig = chatMemoryConfigOptional.get();

        // 可以在这里添加删除前的业务规则验证
        // 例如：检查是否有绑定的记忆实例需要先解绑

        // 执行删除操作
        ChatMemoryConfigDomainRepository.remove(chatMemoryConfig);
        return true;
    }

    @Override
    public Class<DeleteChatMemoryConfigCommand> getSupportedCommandType() {

        return DeleteChatMemoryConfigCommand.class;
    }
}
