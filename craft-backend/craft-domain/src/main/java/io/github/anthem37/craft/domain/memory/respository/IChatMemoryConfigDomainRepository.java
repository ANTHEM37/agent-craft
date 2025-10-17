package io.github.anthem37.craft.domain.memory.respository;

import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.easy.ddd.domain.repository.IDomainRepository;

/**
 * 聊天记忆配置域仓储接口
 *
 * @author hb28301
 * @since 2025/10/16 14:45:44
 */
public interface IChatMemoryConfigDomainRepository extends IDomainRepository<ChatMemoryConfig, Long> {

    /**
     * 绑定记忆配置
     *
     * @param chatMemoryConfig 聊天记忆配置
     * @param memoryId         记忆ID
     */
    void bindMemory(ChatMemoryConfig chatMemoryConfig, Long memoryId);
}
