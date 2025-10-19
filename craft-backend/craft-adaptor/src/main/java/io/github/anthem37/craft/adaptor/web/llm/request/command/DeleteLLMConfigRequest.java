package io.github.anthem37.craft.adaptor.web.llm.request.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 删除LLM配置请求（Web DTO）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DeleteLLMConfigRequest {

    @NotNull(message = "ID不能为空")
    private Long id;
}
