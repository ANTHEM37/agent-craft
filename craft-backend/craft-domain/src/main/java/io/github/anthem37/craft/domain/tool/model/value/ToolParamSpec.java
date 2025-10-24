package io.github.anthem37.craft.domain.tool.model.value;

import io.github.anthem37.easy.ddd.domain.model.IValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 单个参数规范
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToolParamSpec implements IValueObject {

    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数类型
     */
    private ToolParamType type;
    /**
     * 参数描述
     */
    private String description;
    /**
     * 是否必填
     */
    private Boolean required;
    /**
     * 可选枚举值（仅当 type = ENUM 时有效）
     */
    private List<String> enumValues;
    /**
     * 默认值（字符串存储，执行器自行解析）
     */
    private String defaultValue;

    /**
     * 对象类型的子属性（仅当 type = OBJECT 时有效）
     */
    private List<ToolParamSpec> properties;

    /**
     * 数组元素的模式（仅当 type = ARRAY 时有效）
     */
    private ToolParamSpec items;

}