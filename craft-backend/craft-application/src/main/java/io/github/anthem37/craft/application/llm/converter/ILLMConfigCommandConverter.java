package io.github.anthem37.craft.application.llm.converter;

import io.github.anthem37.craft.application.llm.dto.command.CreateLLMConfigCommand;
import io.github.anthem37.craft.application.llm.dto.command.UpdateLLMConfigCommand;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hb28301
 * @since 2025/10/11 15:46:24
 */
@Mapper
public interface ILLMConfigCommandConverter {

    ILLMConfigCommandConverter INSTANCE = Mappers.getMapper(ILLMConfigCommandConverter.class);

    LLMConfig toDomain(CreateLLMConfigCommand po);

    LLMConfig toDomain(UpdateLLMConfigCommand po);

}
