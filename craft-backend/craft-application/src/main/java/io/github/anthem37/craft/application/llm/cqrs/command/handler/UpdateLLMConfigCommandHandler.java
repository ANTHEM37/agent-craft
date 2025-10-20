package io.github.anthem37.craft.application.llm.cqrs.command.handler;

import io.github.anthem37.craft.application.llm.assembler.command.LLMConfigCommandAssembler;
import io.github.anthem37.craft.application.llm.cqrs.command.UpdateLLMConfigCommand;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.domain.llm.repository.ILLMConfigDomainRepository;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/11 17:14:03
 */
@Component
@RequiredArgsConstructor
public class UpdateLLMConfigCommandHandler implements ICommandHandler<UpdateLLMConfigCommand, Boolean> {

    private final ILLMConfigDomainRepository llmConfigDomainRepository;

    @Override
    public Boolean handle(UpdateLLMConfigCommand command) {
        // 转换为领域对象
        LLMConfig llmConfig = LLMConfigCommandAssembler.INSTANCE.toDomain(command);

        // 验证聚合根的业务不变性
        llmConfig.validateInvariants();

        // 更新聚合根
        llmConfigDomainRepository.update(llmConfig);
        return true;
    }

    @Override
    public Class<UpdateLLMConfigCommand> getSupportedCommandType() {

        return UpdateLLMConfigCommand.class;
    }
}
