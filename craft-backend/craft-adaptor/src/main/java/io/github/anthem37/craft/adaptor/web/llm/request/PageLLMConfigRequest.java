package io.github.anthem37.craft.adaptor.web.llm.request;

import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 分页查询LLM配置请求（Web DTO）
 *
 * @author hb28301
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageLLMConfigRequest {

    @Min(value = 1, message = "当前页码必须大于等于1")
    @Max(value = 100, message = "当前页码必须小于等于100")
    private long current = 1;

    @Min(value = 1, message = "每页数量必须大于等于1")
    @Max(value = 100, message = "每页数量必须小于等于100")
    private long size = 10;

    private String configName;

    private LLMProvider provider;

    private String modelName;
}
