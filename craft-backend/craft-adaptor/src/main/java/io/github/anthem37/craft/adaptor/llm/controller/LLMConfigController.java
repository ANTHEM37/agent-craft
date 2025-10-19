package io.github.anthem37.craft.adaptor.web.llm.controller;

import io.github.anthem37.craft.adaptor.common.dto.response.Response;
import io.github.anthem37.craft.adaptor.web.llm.request.command.*;
import io.github.anthem37.craft.adaptor.web.llm.request.query.*;
import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.application.llm.service.ILLMConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * LLM配置控制器
 *
 * @author hb28301
 * @since 2025/10/11 17:46:01
 */
@RestController
@RequestMapping("/llm-config")
@RequiredArgsConstructor
public class LLMConfigController {

    private final ILLMConfigService llmConfigService;
    private final io.github.anthem37.craft.adaptor.web.llm.assembler.LLMConfigWebAssembler webAssembler;
    private final io.github.anthem37.craft.adaptor.web.llm.assembler.LLMConfigResponseAssembler responseAssembler;

    /**
     * 创建配置
     */
    @PostMapping("/create")
    public Response<Boolean> create(@Valid @RequestBody CreateLLMConfigRequest request) {
        Boolean ok = llmConfigService.create(webAssembler.toCommand(request));
        return ok ? Response.ok(ok) : Response.fail(1, "创建失败");
    }

    /**
     * 更新配置
     */
    @PostMapping("/update")
    public Response<Boolean> update(@Valid @RequestBody UpdateLLMConfigRequest request) {
        Boolean ok = llmConfigService.update(webAssembler.toCommand(request));
        return ok ? Response.ok(ok) : Response.fail(1, "更新失败");
    }

    /**
     * 删除配置
     */
    @PostMapping("/delete")
    public Response<Boolean> delete(@Valid @RequestBody DeleteLLMConfigRequest request) {
        Boolean ok = llmConfigService.delete(webAssembler.toCommand(request));
        return ok ? Response.ok(ok) : Response.fail(1, "删除失败");
    }

    /**
     * 查询单个配置
     */
    @PostMapping("/findOne")
    public Response<io.github.anthem37.craft.adaptor.web.llm.response.LLMConfigResponse> findOne(@Valid @RequestBody FindOneLLMConfigRequest request) {
        io.github.anthem37.craft.adaptor.web.llm.response.LLMConfigResponse resp = responseAssembler.toResponse(llmConfigService.findOne(webAssembler.toQuery(request)));
        return Response.ok(resp);
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public Response<List<io.github.anthem37.craft.adaptor.web.llm.response.LLMConfigResponse>> list(@Valid @RequestBody ListLLMConfigRequest request) {
        List<io.github.anthem37.craft.adaptor.web.llm.response.LLMConfigResponse> list = responseAssembler.toResponseList(llmConfigService.list(webAssembler.toQuery(request)));
        return Response.ok(list);
    }

    /**
     * 统计数量
     */
    @PostMapping("/count")
    public Response<Long> count(@Valid @RequestBody CountLLMConfigRequest request) {
        Long count = llmConfigService.count(webAssembler.toQuery(request));
        return Response.ok(count);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public Response<PageDTO<io.github.anthem37.craft.adaptor.web.llm.response.LLMConfigResponse>> page(@Valid @RequestBody PageLLMConfigRequest request) {
        PageDTO<io.github.anthem37.craft.adaptor.web.llm.response.LLMConfigResponse> page = responseAssembler.toResponsePage(llmConfigService.page(webAssembler.toQuery(request)));
        return Response.ok(page);
    }
}
