package io.github.anthem37.craft.adaptor.web.memory.assembler;

import io.github.anthem37.craft.adaptor.web.memory.response.ChatMemoryConfigResponse;
import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChatMemoryConfigResponseAssembler {

    ChatMemoryConfigResponse toResponse(ChatMemoryConfigDTO dto);

    List<ChatMemoryConfigResponse> toResponseList(List<ChatMemoryConfigDTO> dtos);

    default PageDTO<ChatMemoryConfigResponse> toResponsePage(PageDTO<ChatMemoryConfigDTO> pageDTO) {
        return PageDTO.of(pageDTO.getCurrent(), pageDTO.getSize(), pageDTO.getTotal(), toResponseList(pageDTO.getRecords()));
    }
}
