package io.github.anthem37.craft.application.llm.cqrs.command.handler;

import io.github.anthem37.craft.application.llm.cqrs.command.DeleteLLMConfigCommand;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.domain.llm.repository.ILLMConfigDomainRepository;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author hb28301
 * @since 2025/10/11 17:13:40
 */
@Component
@RequiredArgsConstructor
public class DeleteLLMConfigCommandHandler implements ICommandHandler<DeleteLLMConfigCommand, Boolean> {

    private final ILLMConfigDomainRepository llmConfigDomainRepository;

    @Override
    public Boolean handle(DeleteLLMConfigCommand command) {
        // 查找聚合根
        Optional<LLMConfig> llmConfigOptional = llmConfigDomainRepository.findById(command.getId());
        if (llmConfigOptional.isEmpty()) {
            return true;
        }

        LLMConfig llmConfig = llmConfigOptional.get();

        // 可以在这里添加删除前的业务规则验证
        // 例如：检查是否有其他实体依赖此配置

        // 执行删除操作
        llmConfigDomainRepository.remove(llmConfig);
        return true;
    }

    @Override
    public Class<DeleteLLMConfigCommand> getSupportedCommandType() {

        return DeleteLLMConfigCommand.class;
    }
}
