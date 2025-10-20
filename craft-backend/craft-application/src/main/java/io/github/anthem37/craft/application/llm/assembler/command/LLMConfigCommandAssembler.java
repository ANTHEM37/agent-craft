package io.github.anthem37.craft.application.llm.assembler.command;

import io.github.anthem37.craft.application.llm.cqrs.command.CreateLLMConfigCommand;
import io.github.anthem37.craft.application.llm.cqrs.command.UpdateLLMConfigCommand;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hb28301
 * @since 2025/10/11 15:46:24
 */
@Mapper
public interface LLMConfigCommandAssembler {

    LLMConfigCommandAssembler INSTANCE = Mappers.getMapper(LLMConfigCommandAssembler.class);

    LLMConfig toDomain(CreateLLMConfigCommand command);

    LLMConfig toDomain(UpdateLLMConfigCommand command);

}
