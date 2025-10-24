package io.github.anthem37.craft.domain.tool.model.entity;

import io.github.anthem37.craft.domain.sharedkernel.model.entity.BaseAggregateRoot;
import io.github.anthem37.craft.domain.tool.event.CreatedToolConfigEvent;
import io.github.anthem37.craft.domain.tool.event.DeletedToolConfigEvent;
import io.github.anthem37.craft.domain.tool.event.UpdatedToolConfigEvent;
import io.github.anthem37.craft.domain.tool.model.value.ToolParamSpec;
import io.github.anthem37.craft.domain.tool.model.value.ToolParamsSchema;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * 工具配置聚合根
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToolConfig extends BaseAggregateRoot {

    /**
     * 配置名称（显示用途）
     */
    private String configName;

    /**
     * 注册到Spring的Bean名称（用于LLM ExtraInfo.toolNames引用）
     */
    private String beanName;

    /**
     * 工具描述
     */
    private String description;

    /**
     * 入参Schema
     */
    private ToolParamsSchema paramsSchema;

    /**
     * 出参Schema（仅用于文档/提示描述，非强制）
     */
    private ToolParamsSchema resultSchema;

    /**
     * 标记为创建，触发事件
     */
    public void markAsCreated() {
        addDomainEvent(new CreatedToolConfigEvent(this));
    }

    /**
     * 标记为更新，触发事件
     */
    public void markAsUpdated() {
        addDomainEvent(new UpdatedToolConfigEvent(this));
    }

    /**
     * 标记为删除，触发事件
     */
    public void markAsDeleted() {
        addDomainEvent(new DeletedToolConfigEvent(this));
    }

    /**
     * 校验聚合根不变式
     */
    public void validateInvariants() {
        Assert.hasText(configName, "配置名称不能为空");
        Assert.hasText(beanName, "Bean名称不能为空");
        Assert.notNull(paramsSchema, "参数Schema不能为空");
        validateProperties(paramsSchema);
        if (resultSchema != null) {
            validateProperties(resultSchema);
        }
    }

    private void validateProperties(ToolParamsSchema schema) {
        Set<String> names = new HashSet<>();
        if (schema.getProperties() != null) {
            for (ToolParamSpec spec : schema.getProperties()) {
                Assert.hasText(spec.getName(), "参数名不能为空");
                Assert.notNull(spec.getType(), "参数类型不能为空");
                Assert.isTrue(names.add(spec.getName()), "参数名不能重复: " + spec.getName());
                // 对象/数组的额外校验
                switch (spec.getType()) {
                    case OBJECT -> {
                        Assert.notNull(spec.getProperties(), "OBJECT类型需要定义子属性: " + spec.getName());
                    }
                    case ARRAY -> {
                        Assert.notNull(spec.getItems(), "ARRAY类型需要定义元素模式: " + spec.getName());
                    }
                    default -> {
                    }
                }
            }
        }
    }
}