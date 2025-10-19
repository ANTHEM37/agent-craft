package io.github.anthem37.craft.application.llm.cqrs.handler.command;

import io.github.anthem37.craft.application.llm.assembler.command.LLMConfigCommandAssembler;
import io.github.anthem37.craft.application.llm.dto.command.CreateLLMConfigCommand;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.domain.llm.repository.ILLMConfigDomainRepository;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 创建LLM配置命令处理程序
 *
 * @author hb28301
 * @since 2025/10/11 17:13:05
 */
@Component
@RequiredArgsConstructor
public class CreateLLMConfigCommandHandler implements ICommandHandler<CreateLLMConfigCommand, Boolean> {

    private final ILLMConfigDomainRepository llmConfigDomainRepository;

    @Override
    public Boolean handle(CreateLLMConfigCommand command) {
        // 转换为领域对象
        LLMConfig llmConfig = LLMConfigCommandAssembler.INSTANCE.toDomain(command);

        // 验证聚合根的业务不变性
        llmConfig.validateInvariants();

        // 保存聚合根
        llmConfigDomainRepository.save(llmConfig);
        return true;
    }

    @Override
    public Class<CreateLLMConfigCommand> getSupportedCommandType() {

        return CreateLLMConfigCommand.class;
    }
}
