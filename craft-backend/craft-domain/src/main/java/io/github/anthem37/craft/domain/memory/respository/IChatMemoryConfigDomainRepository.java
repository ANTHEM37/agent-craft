package io.github.anthem37.craft.domain.memory.respository;

import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.easy.ddd.domain.repository.IDomainRepository;

/**
 * 聊天记忆配置领域仓储接口
 *
 * @author hb28301
 * @since 2025/10/17 15:00:00
 */
public interface IChatMemoryConfigDomainRepository extends IDomainRepository<ChatMemoryConfig, Long> {

    /**
     * 检查记忆配置是否已绑定到指定记忆实例
     *
     * @param configId 配置ID
     * @param memoryId 记忆ID
     * @return 是否已绑定
     */
    boolean isMemoryBound(Long configId, Long memoryId);

    /**
     * 创建记忆配置与记忆实例的绑定关系
     *
     * @param configId 配置ID
     * @param memoryId 记忆ID
     */
    void createMemoryBinding(Long configId, Long memoryId);

    /**
     * 创建记忆配置与记忆实例的绑定关系，并触发相应的领域事件
     *
     * @param chatMemoryConfig 聊天记忆配置聚合根
     * @param memoryId         记忆ID
     */
    void createMemoryBinding(ChatMemoryConfig chatMemoryConfig, Long memoryId);

    /**
     * 删除记忆配置与记忆实例的绑定关系
     *
     * @param configId 配置ID
     * @param memoryId 记忆ID
     */
    void removeMemoryBinding(Long configId, Long memoryId);

    /**
     * 删除记忆配置与记忆实例的绑定关系，并触发相应的领域事件
     *
     * @param chatMemoryConfig 聊天记忆配置聚合根
     * @param memoryId         记忆ID
     */
    void removeMemoryBinding(ChatMemoryConfig chatMemoryConfig, Long memoryId);
}
