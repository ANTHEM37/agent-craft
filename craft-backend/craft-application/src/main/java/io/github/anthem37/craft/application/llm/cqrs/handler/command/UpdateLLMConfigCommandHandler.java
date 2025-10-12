package io.github.anthem37.craft.application.llm.cqrs.handler.command;

import io.github.anthem37.craft.application.llm.converter.ILLMConfigCommandConverter;
import io.github.anthem37.craft.application.llm.dto.command.UpdateLLMConfigCommand;
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
        LLMConfig llmConfig = ILLMConfigCommandConverter.INSTANCE.toDomain(command);
        llmConfigDomainRepository.update(llmConfig);
        return true;
    }

    @Override
    public Class<UpdateLLMConfigCommand> getSupportedCommandType() {

        return UpdateLLMConfigCommand.class;
    }
}
