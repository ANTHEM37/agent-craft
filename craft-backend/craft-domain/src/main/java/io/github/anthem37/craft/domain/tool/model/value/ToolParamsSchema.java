package io.github.anthem37.craft.domain.tool.model.value;

import io.github.anthem37.easy.ddd.domain.model.IValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具参数 JSON Schema（值对象）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToolParamsSchema implements IValueObject {

    /**
     * 工具参数列表（属性集）
     */
    private List<ToolParamSpec> properties = new ArrayList<>();

    /**
     * 统一的schema描述，可选
     */
    private String description;
}