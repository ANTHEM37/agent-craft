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
        // 查找聚合根
        Optional<ChatMemoryConfig> chatMemoryConfigOptional = ChatMemoryConfigDomainRepository.findById(command.getId());
        if (chatMemoryConfigOptional.isEmpty()) {
            return true; // 幂等性：如果不存在则认为删除成功
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
