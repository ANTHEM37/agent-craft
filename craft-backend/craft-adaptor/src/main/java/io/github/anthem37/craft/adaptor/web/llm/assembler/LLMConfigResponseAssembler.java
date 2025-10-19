package io.github.anthem37.craft.adaptor.web.llm.assembler;

import io.github.anthem37.craft.adaptor.web.llm.response.LLMConfigResponse;
import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LLMConfigResponseAssembler {

    LLMConfigResponse toResponse(LLMConfigDTO dto);

    List<LLMConfigResponse> toResponseList(List<LLMConfigDTO> dtos);

    default PageDTO<LLMConfigResponse> toResponsePage(PageDTO<LLMConfigDTO> pageDTO) {
        return PageDTO.of(pageDTO.getCurrent(), pageDTO.getSize(), pageDTO.getTotal(), toResponseList(pageDTO.getRecords()));
    }
}
