package io.github.anthem37.craft.adaptor.web.llm.request;

import io.github.anthem37.craft.domain.llm.model.value.ExtraInfo;
import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 更新LLM配置请求（Web DTO）
 *
 * @author hb28301
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UpdateLLMConfigRequest {

    @NotNull(message = "ID不能为空")
    private Long id;

    @NotBlank(message = "配置名称不能为空")
    private String configName;

    @NotBlank(message = "模型提供商不能为空")
    private LLMProvider provider;

    @NotBlank(message = "API基础URL不能为空")
    private String baseUrl;

    @NotBlank(message = "API密钥不能为空")
    private String apiKey;

    @NotBlank(message = "模型名称不能为空")
    private String modelName;

    @Valid
    private ExtraInfo extraInfo;

    @NotBlank(message = "模型描述不能为空")
    private String description;
}
