package io.github.anthem37.craft.adaptor.web.llm.response;

import io.github.anthem37.craft.domain.llm.model.value.ExtraInfo;
import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author hb28301
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LLMConfigResponse {

    /**
     * 配置ID
     */
    private Long id;

    /**
     * 配置名称
     */
    private String configName;

    /**
     * 模型提供商
     */
    private LLMProvider provider;

    /**
     * API基础URL
     */
    private String baseUrl;

    /**
     * API密钥
     */
    private String apiKey;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 额外配置信息
     */
    private ExtraInfo extraInfo;

    /**
     * 模型描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 创建人ID
     */
    private Long createdBy;

    /**
     * 创建人用户名
     */
    private String createdByUsername;

    /**
     * 更新人ID
     */
    private Long updatedBy;

    /**
     * 更新人用户名
     */
    private String updatedByUsername;

    /**
     * 逻辑删除标记（0 未删除，!= 0 删除）
     */
    private Long deleted;
}
