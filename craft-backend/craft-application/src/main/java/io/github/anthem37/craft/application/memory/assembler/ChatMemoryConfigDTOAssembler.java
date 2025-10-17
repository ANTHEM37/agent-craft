package io.github.anthem37.craft.application.memory.assembler;

import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 负责 ChatMemoryConfig 的 Domain <-> DTO 映射（应用层装配器）
 */
@Mapper
public interface ChatMemoryConfigDTOAssembler {

    ChatMemoryConfigDTOAssembler INSTANCE = Mappers.getMapper(ChatMemoryConfigDTOAssembler.class);

    ChatMemoryConfigDTO toDTO(ChatMemoryConfig domain);

    ChatMemoryConfig toDomain(ChatMemoryConfigDTO dto);
}