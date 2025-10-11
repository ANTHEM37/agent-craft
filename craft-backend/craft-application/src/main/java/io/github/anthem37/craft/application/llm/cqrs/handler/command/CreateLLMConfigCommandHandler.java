package io.github.anthem37.craft.application.llm.cqrs.handler.command;

import io.github.anthem37.craft.application.llm.converter.ILLMConfigCommandConverter;
import io.github.anthem37.craft.application.llm.dto.command.CreateLLMConfigCommand;
import io.github.anthem37.craft.domain.llm.repository.ILLMConfigDomainRepository;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 创建LLM配置命令处理程序
 *
 * @author hb28301
 * @date 2025/10/11 17:13:05
 */
@Component
@RequiredArgsConstructor
public class CreateLLMConfigCommandHandler implements ICommandHandler<CreateLLMConfigCommand, Boolean> {

    private final ILLMConfigDomainRepository llmConfigDomainRepository;

    @Override
    public Boolean handle(CreateLLMConfigCommand command) {
        llmConfigDomainRepository.save(ILLMConfigCommandConverter.INSTANCE.toDomain(command));
        return true;
    }

    @Override
    public Class<CreateLLMConfigCommand> getSupportedCommandType() {

        return CreateLLMConfigCommand.class;
    }
}
