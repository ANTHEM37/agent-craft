package io.github.anthem37.craft.adaptor.web.llm.request;

import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 统计LLM配置请求（Web DTO）
 *
 * @author hb28301
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CountLLMConfigRequest {

    private String configName;

    private LLMProvider provider;

    private String modelName;
}
