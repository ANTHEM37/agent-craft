package io.github.anthem37.craft.application.llm.dto;

import io.github.anthem37.craft.domain.llm.model.value.ExtraInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
public class LLMConfigDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 配置名称，用于标识不同的模型配置
     */
    @NotBlank(message = "配置名称不能为空")
    private String configName;

    /**
     * 自定义API基础URL，用于指定自定义的OpenAI API地址
     */
    @NotBlank(message = "API基础URL不能为空")
    private String baseUrl;

    /**
     * API密钥，用于身份验证
     */
    @NotBlank(message = "API密钥不能为空")
    private String apiKey;

    /**
     * 模型名称，指定要使用的具体模型
     */
    @NotBlank(message = "模型名称不能为空")
    private String modelName;

    /**
     * 额外配置信息，包含模型的详细参数
     */
    @Valid
    private ExtraInfo extraInfo;

    /**
     * 模型描述，用于说明模型的功能和特性
     */
    @NotBlank(message = "模型描述不能为空")
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
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建人用户名
     */
    private String createdByUsername;

    /**
     * 更新人
     */
    private Long updatedBy;

    /**
     * 更新人用户名
     */
    private String updatedByUsername;

    /**
     * 逻辑删除标记（0 未删除，1 删除）
     */
    private Long deleted;
}
