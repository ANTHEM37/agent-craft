package io.github.anthem37.craft.adaptor.web.llm.assembler;

import io.github.anthem37.craft.adaptor.web.llm.request.command.CreateLLMConfigRequest;
import io.github.anthem37.craft.adaptor.web.llm.request.command.DeleteLLMConfigRequest;
import io.github.anthem37.craft.adaptor.web.llm.request.command.UpdateLLMConfigRequest;
import io.github.anthem37.craft.adaptor.web.llm.request.query.CountLLMConfigRequest;
import io.github.anthem37.craft.adaptor.web.llm.request.query.FindOneLLMConfigRequest;
import io.github.anthem37.craft.adaptor.web.llm.request.query.ListLLMConfigRequest;
import io.github.anthem37.craft.adaptor.web.llm.request.query.PageLLMConfigRequest;
import io.github.anthem37.craft.application.llm.dto.command.CreateLLMConfigCommand;
import io.github.anthem37.craft.application.llm.dto.command.DeleteLLMConfigCommand;
import io.github.anthem37.craft.application.llm.dto.command.UpdateLLMConfigCommand;
import io.github.anthem37.craft.application.llm.dto.query.CountLLMConfigQuery;
import io.github.anthem37.craft.application.llm.dto.query.FindOneLLMConfigQuery;
import io.github.anthem37.craft.application.llm.dto.query.ListLLMConfigQuery;
import io.github.anthem37.craft.application.llm.dto.query.PageLLMConfigQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LLMConfigWebAssembler {

    LLMConfigWebAssembler INSTANCE = Mappers.getMapper(LLMConfigWebAssembler.class);

    // command mappings
    CreateLLMConfigCommand toCommand(CreateLLMConfigRequest request);

    UpdateLLMConfigCommand toCommand(UpdateLLMConfigRequest request);

    DeleteLLMConfigCommand toCommand(DeleteLLMConfigRequest request);

    // query mappings
    FindOneLLMConfigQuery toQuery(FindOneLLMConfigRequest request);

    ListLLMConfigQuery toQuery(ListLLMConfigRequest request);

    PageLLMConfigQuery toQuery(PageLLMConfigRequest request);

    CountLLMConfigQuery toQuery(CountLLMConfigRequest request);
}
