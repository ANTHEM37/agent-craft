package io.github.anthem37.craft.adaptor.web.memory.controller;

import io.github.anthem37.craft.adaptor.common.dto.response.Response;
import io.github.anthem37.craft.adaptor.web.memory.request.command.CreateChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.command.DeleteChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.command.UpdateChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.query.CountChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.query.FindOneChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.query.ListChatMemoryConfigRequest;
import io.github.anthem37.craft.adaptor.web.memory.request.query.PageChatMemoryConfigRequest;
import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.memory.service.IChatMemoryConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 记忆配置控制器
 *
 * @author hb28301
 * @since 2025/10/11 17:46:01
 */
@RestController
@RequestMapping("/chat-memory-config")
@RequiredArgsConstructor
public class ChatMemoryConfigController {

    private final IChatMemoryConfigService chatMemoryConfigService;
    private final io.github.anthem37.craft.adaptor.web.memory.assembler.ChatMemoryConfigWebAssembler webAssembler;
    private final io.github.anthem37.craft.adaptor.web.memory.assembler.ChatMemoryConfigResponseAssembler responseAssembler;

    /**
     * 创建配置
     */
    @PostMapping("/create")
    public Response<Boolean> create(@Valid @RequestBody CreateChatMemoryConfigRequest request) {
        Boolean ok = chatMemoryConfigService.create(webAssembler.toCommand(request));
        return ok ? Response.ok(ok) : Response.fail(1, "创建失败");
    }

    /**
     * 更新配置
     */
    @PostMapping("/update")
    public Response<Boolean> update(@Valid @RequestBody UpdateChatMemoryConfigRequest request) {
        Boolean ok = chatMemoryConfigService.update(webAssembler.toCommand(request));
        return ok ? Response.ok(ok) : Response.fail(1, "更新失败");
    }

    /**
     * 删除配置
     */
    @PostMapping("/delete")
    public Response<Boolean> delete(@Valid @RequestBody DeleteChatMemoryConfigRequest request) {
        Boolean ok = chatMemoryConfigService.delete(webAssembler.toCommand(request));
        return ok ? Response.ok(ok) : Response.fail(1, "删除失败");
    }

    /**
     * 查询单个配置
     */
    @PostMapping("/findOne")
    public Response<io.github.anthem37.craft.adaptor.web.memory.response.ChatMemoryConfigResponse> findOne(@Valid @RequestBody FindOneChatMemoryConfigRequest request) {
        io.github.anthem37.craft.adaptor.web.memory.response.ChatMemoryConfigResponse resp = responseAssembler.toResponse(chatMemoryConfigService.findOne(webAssembler.toQuery(request)));
        return Response.ok(resp);
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public Response<List<io.github.anthem37.craft.adaptor.web.memory.response.ChatMemoryConfigResponse>> list(@Valid @RequestBody ListChatMemoryConfigRequest request) {
        List<io.github.anthem37.craft.adaptor.web.memory.response.ChatMemoryConfigResponse> list = responseAssembler.toResponseList(chatMemoryConfigService.list(webAssembler.toQuery(request)));
        return Response.ok(list);
    }

    /**
     * 统计数量
     */
    @PostMapping("/count")
    public Response<Long> count(@Valid @RequestBody CountChatMemoryConfigRequest request) {
        Long count = chatMemoryConfigService.count(webAssembler.toQuery(request));
        return Response.ok(count);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public Response<PageDTO<io.github.anthem37.craft.adaptor.web.memory.response.ChatMemoryConfigResponse>> page(@Valid @RequestBody PageChatMemoryConfigRequest request) {
        PageDTO<io.github.anthem37.craft.adaptor.web.memory.response.ChatMemoryConfigResponse> page = responseAssembler.toResponsePage(chatMemoryConfigService.page(webAssembler.toQuery(request)));
        return Response.ok(page);
    }
}
