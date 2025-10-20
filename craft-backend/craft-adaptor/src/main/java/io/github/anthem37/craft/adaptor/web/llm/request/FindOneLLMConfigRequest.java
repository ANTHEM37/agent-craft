package io.github.anthem37.craft.adaptor.web.llm.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询单个LLM配置请求（Web DTO）
 *
 * @author hb28301
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FindOneLLMConfigRequest {

    @NotNull(message = "模型配置ID不能为空")
    private Long id;
}
