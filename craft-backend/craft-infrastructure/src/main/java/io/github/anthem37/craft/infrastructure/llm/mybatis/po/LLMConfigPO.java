package io.github.anthem37.craft.infrastructure.llm.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.anthem37.craft.domain.llm.model.value.ExtraInfo;
import io.github.anthem37.craft.infrastructure.common.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author hb28301
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_chat_model_config")
public class LLMConfigPO extends BasePO {

    /**
     * 配置名称
     */
    @TableField(value = "config_name")
    private String configName;

    /**
     * 自定义API基础URL
     */
    @TableField(value = "base_url")
    private String baseUrl;

    /**
     * API密钥
     */
    @TableField(value = "api_key")
    private String apiKey;

    /**
     * 模型名称
     */
    @TableField(value = "model_name")
    private String modelName;


    /**
     * 额外配置信息
     */
    @TableField(value = "extra_info", typeHandler = JacksonTypeHandler.class)
    private ExtraInfo extraInfo;

    /**
     * 模型描述
     */
    @TableField(value = "description")
    private String description;
}