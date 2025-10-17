package io.github.anthem37.craft.domain.memory.model.entity;

import cn.hutool.json.JSONUtil;
import io.github.anthem37.craft.domain.common.model.entity.BaseAggregateRoot;
import io.github.anthem37.craft.domain.memory.event.*;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryConfigParams;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天内存配置实体类
 *
 * @author hb28301
 * @since 2025/10/14 10:20:06
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatMemoryConfig extends BaseAggregateRoot {

    /**
     * 配置名称，用于标识不同的记忆配置
     */
    private String configName;

    /**
     * 记忆类型
     */
    private ChatMemoryType chatMemoryType;

    /**
     * 记忆参数
     */
    private ChatMemoryConfigParams params;

    /**
     * 记忆描述
     */
    private String description;

    /**
     * 解析记忆参数
     *
     * @param clazz 解析后的内存配置类
     * @return 解析后的内存配置
     */
    public <T extends ChatMemoryConfigParams> T parseParams(Class<T> clazz) {

        return JSONUtil.toBean(JSONUtil.toJsonStr(params), clazz);
    }

    /**
     * 标记为已创建，触发 CreatedChatMemoryConfigEvent 事件
     */
    public void markAsCreated() {
        addDomainEvent(new CreatedChatMemoryConfigEvent(this));
    }

    /**
     * 标记为已更新，触发 UpdatedChatMemoryConfigEvent 事件
     */
    public void markAsUpdated() {
        addDomainEvent(new UpdatedChatMemoryConfigEvent(this));
    }

    /**
     * 标记为已删除，触发 DeletedChatMemoryConfigEvent 事件
     */
    public void markAsDeleted() {
        addDomainEvent(new DeletedChatMemoryConfigEvent(this));
    }

    /**
     * 标记为已经绑定记忆，触发 DeletedChatMemoryConfigEvent 事件
     */

    /**
     * 标记为已经绑定记忆，触发 BindMemoryChatMemoryConfigEvent 事件
     *
     * @param memoryId 记忆ID
     */
    public void markAsBindMemory(Long memoryId) {
        addDomainEvent(new BindMemoryChatMemoryConfigEvent(this, memoryId));
    }

    /**
     * 标记为已经解绑记忆，触发 UnBindMemoryChatMemoryConfigEvent 事件
     *
     * @param memoryId 记忆ID
     */
    public void markAsUnbindMemory(Long memoryId) {
        addDomainEvent(new UnbindMemoryChatMemoryConfigEvent(this, memoryId));
    }

    /**
     * 验证聚合根的不变性
     * 
     * @throws IllegalArgumentException 当不变性被违反时抛出
     */
    public void validateInvariants() {
        validateRequiredFields();
        validateChatMemoryType();
        validateParams();
    }

    /**
     * 验证必填字段
     */
    private void validateRequiredFields() {
        Assert.hasText(configName, "配置名称不能为空");
        Assert.notNull(chatMemoryType, "记忆类型不能为空");
    }

    /**
     * 验证记忆类型
     */
    private void validateChatMemoryType() {
        // 这里可以添加对特定记忆类型的验证逻辑
        // 例如检查记忆类型是否被支持等
    }

    /**
     * 验证记忆参数
     */
    private void validateParams() {
        if (params != null) {
            // 这里可以添加对记忆参数的验证逻辑
            // 例如检查参数的有效性等
        }
    }
}
