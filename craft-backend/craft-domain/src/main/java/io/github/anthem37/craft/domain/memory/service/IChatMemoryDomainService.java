package io.github.anthem37.craft.domain.memory.service;

import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;

/**
 * 聊天记忆领域服务接口
 *
 * @author hb28301
 * @since 2025/10/17 15:00:00
 */
public interface IChatMemoryDomainService {

    /**
     * 绑定记忆配置到具体的记忆实例
     *
     * @param chatMemoryConfig 聊天记忆配置
     * @param memoryId         记忆ID
     */
    void bindMemoryToConfig(ChatMemoryConfig chatMemoryConfig, Long memoryId);

    /**
     * 解绑记忆配置
     *
     * @param chatMemoryConfig 聊天记忆配置
     * @param memoryId         记忆ID
     */
    void unbindMemoryFromConfig(ChatMemoryConfig chatMemoryConfig, Long memoryId);
}