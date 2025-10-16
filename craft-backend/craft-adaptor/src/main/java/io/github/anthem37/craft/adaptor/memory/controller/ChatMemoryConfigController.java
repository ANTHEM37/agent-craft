package io.github.anthem37.craft.adaptor.memory.controller;

import io.github.anthem37.craft.adaptor.common.dto.response.Response;
import io.github.anthem37.craft.adaptor.memory.controller.request.*;
import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
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
@RequestMapping("/llm-config")
@RequiredArgsConstructor
public class ChatMemoryConfigController {

    private final IChatMemoryConfigService ChatMemoryConfigService;

    /**
     * 创建配置
     */
    @PostMapping("/create")
    public Response<Boolean> create(@Valid @RequestBody CreateChatMemoryConfigRequest request) {
        Boolean ok = ChatMemoryConfigService.create(request);
        return ok ? Response.ok(ok) : Response.fail(1, "创建失败");
    }

    /**
     * 更新配置
     */
    @PostMapping("/update")
    public Response<Boolean> update(@Valid @RequestBody UpdateChatMemoryConfigRequest request) {
        Boolean ok = ChatMemoryConfigService.update(request);
        return ok ? Response.ok(ok) : Response.fail(1, "更新失败");
    }

    /**
     * 删除配置
     */
    @PostMapping("/delete")
    public Response<Boolean> delete(@Valid @RequestBody DeleteChatMemoryConfigRequest request) {
        Boolean ok = ChatMemoryConfigService.delete(request);
        return ok ? Response.ok(ok) : Response.fail(1, "删除失败");
    }

    /**
     * 查询单个配置
     */
    @PostMapping("/findOne")
    public Response<ChatMemoryConfigDTO> findOne(@Valid @RequestBody FindOneChatMemoryConfigRequest request) {
        ChatMemoryConfigDTO dto = ChatMemoryConfigService.findOne(request);
        return Response.ok(dto);
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public Response<List<ChatMemoryConfigDTO>> list(@Valid @RequestBody ListChatMemoryConfigRequest request) {
        List<ChatMemoryConfigDTO> list = ChatMemoryConfigService.list(request);
        return Response.ok(list);
    }

    /**
     * 统计数量
     */
    @PostMapping("/count")
    public Response<Long> count(@Valid @RequestBody CountChatMemoryConfigRequest request) {
        Long count = ChatMemoryConfigService.count(request);
        return Response.ok(count);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public Response<PageDTO<ChatMemoryConfigDTO>> page(@Valid @RequestBody PageChatMemoryConfigRequest request) {
        PageDTO<ChatMemoryConfigDTO> page = ChatMemoryConfigService.page(request);
        return Response.ok(page);
    }
}
