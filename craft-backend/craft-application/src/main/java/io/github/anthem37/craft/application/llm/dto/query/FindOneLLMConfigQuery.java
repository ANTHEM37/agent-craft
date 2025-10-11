package io.github.anthem37.craft.application.llm.dto.query;

import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询单个LLM配置查询
 *
 * @author hb28301
 * @date 2025/10/11 17:10:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FindOneLLMConfigQuery implements IQuery<LLMConfigDTO> {

    /**
     * 要查询的LLM配置ID
     */
    private Long id;
}
