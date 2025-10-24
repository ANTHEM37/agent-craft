package io.github.anthem37.craft.infrastructure.tool.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.anthem37.craft.domain.tool.model.value.ToolParamsSchema;
import io.github.anthem37.craft.infrastructure.common.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 工具配置持久化对象
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_tool_config")
public class ToolConfigPO extends BasePO {

    /**
     * 配置名称
     */
    @TableField("config_name")
    private String configName;

    /**
     * Spring Bean 名称
     */
    @TableField("bean_name")
    private String beanName;

    /**
     * 入参Schema（JSON）
     */
    @TableField(value = "params", typeHandler = JacksonTypeHandler.class)
    private ToolParamsSchema params;

    /**
     * 出参Schema（JSON，仅用于说明）
     */
    @TableField(value = "result", typeHandler = JacksonTypeHandler.class)
    private ToolParamsSchema result;

    /**
     * 描述
     */
    @TableField("description")
    private String description;
}