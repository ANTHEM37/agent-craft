package io.github.anthem37.craft.adaptor.web.llm.assembler;

import io.github.anthem37.craft.adaptor.web.llm.request.*;
import io.github.anthem37.craft.application.llm.cqrs.command.CreateLLMConfigCommand;
import io.github.anthem37.craft.application.llm.cqrs.command.DeleteLLMConfigCommand;
import io.github.anthem37.craft.application.llm.cqrs.command.UpdateLLMConfigCommand;
import io.github.anthem37.craft.application.llm.cqrs.query.CountLLMConfigQuery;
import io.github.anthem37.craft.application.llm.cqrs.query.FindOneLLMConfigQuery;
import io.github.anthem37.craft.application.llm.cqrs.query.ListLLMConfigQuery;
import io.github.anthem37.craft.application.llm.cqrs.query.PageLLMConfigQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hb28301
 */
@Mapper
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
