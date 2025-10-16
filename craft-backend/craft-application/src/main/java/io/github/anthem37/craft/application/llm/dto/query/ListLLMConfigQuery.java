package io.github.anthem37.craft.application.llm.dto.query;

import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 列表查询LLM配置
 *
 * @author hb28301
 * @since 2025/10/11 17:10:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ListLLMConfigQuery implements IQuery<List<LLMConfigDTO>> {

    /**
     * 配置名称，用于筛选指定配置的模型
     */
    private String configName;

    /**
     * 模型提供商（比如openAI、dashscope等）
     */
    private LLMProvider provider;

    /**
     * 模型名称，用于筛选指定模型的配置
     */
    private String modelName;
}
