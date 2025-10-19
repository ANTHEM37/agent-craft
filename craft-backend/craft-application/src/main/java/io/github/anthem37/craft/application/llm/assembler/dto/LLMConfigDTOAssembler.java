package io.github.anthem37.craft.application.llm.assembler.dto;

import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 负责 LLMConfig 的 Domain <-> DTO 映射（应用层装配器）
 */
@Mapper
public interface LLMConfigDTOAssembler {

    LLMConfigDTOAssembler INSTANCE = Mappers.getMapper(LLMConfigDTOAssembler.class);

    LLMConfigDTO toDTO(LLMConfig domain);

    LLMConfig toDomain(LLMConfigDTO dto);
}