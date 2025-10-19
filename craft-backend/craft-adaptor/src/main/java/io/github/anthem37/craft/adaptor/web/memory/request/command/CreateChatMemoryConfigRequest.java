package io.github.anthem37.craft.adaptor.web.memory.request.command;

import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryConfigParams;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 创建记忆配置请求（Web DTO）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreateChatMemoryConfigRequest {

    @NotBlank(message = "配置名称不能为空")
    private String configName;

    @NotNull(message = "记忆类型不能为空")
    private ChatMemoryType chatMemoryType;

    @NotNull(message = "记忆参数不能为空")
    private ChatMemoryConfigParams params;

    @NotBlank(message = "记忆描述不能为空")
    private String description;
}
