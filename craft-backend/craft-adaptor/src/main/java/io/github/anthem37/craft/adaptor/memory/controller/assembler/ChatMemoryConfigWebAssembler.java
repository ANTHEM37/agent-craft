package io.github.anthem37.craft.adaptor.web.memory.assembler;

import io.github.anthem37.craft.adaptor.web.memory.request.command.CreateChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.command.DeleteChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.command.UpdateChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.query.CountChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.query.FindOneChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.query.ListChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.query.PageChatMemoryConfigRequest;
import io.github.anthem37.craft.application.memory.dto.command.CreateChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.dto.command.DeleteChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.dto.command.UpdateChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.dto.query.CountChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.dto.query.FindOneChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.dto.query.ListChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.dto.query.PageChatMemoryConfigQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Web 层装配器：负责将控制器层的 Request 转换为应用层的 Command/Query。
 * 现阶段 Request 继承了 Command/Query，映射为直接复制；
 * 后续可将 Request 改为纯 Web DTO，此装配器保持转换职责不变。
 */
@Mapper(componentModel = "spring")
public interface ChatMemoryConfigWebAssembler {

    ChatMemoryConfigWebAssembler INSTANCE = Mappers.getMapper(ChatMemoryConfigWebAssembler.class);

    // command mappings
    CreateChatMemoryConfigCommand toCommand(CreateChatMemoryConfigRequest request);
    UpdateChatMemoryConfigCommand toCommand(UpdateChatMemoryConfigRequest request);
    DeleteChatMemoryConfigCommand toCommand(DeleteChatMemoryConfigRequest request);

    // query mappings
    FindOneChatMemoryConfigQuery toQuery(FindOneChatMemoryConfigRequest request);
    ListChatMemoryConfigQuery toQuery(ListChatMemoryConfigRequest request);
    PageChatMemoryConfigQuery toQuery(PageChatMemoryConfigRequest request);
    CountChatMemoryConfigQuery toQuery(CountChatMemoryConfigRequest request);
}
