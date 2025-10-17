package io.github.anthem37.craft.domain.memory.service.impl;

import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.respository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.craft.domain.memory.service.IChatMemoryDomainService;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天记忆领域服务实现
 *
 * @author hb28301
 * @since 2025/10/17 15:00:00
 */
@Slf4j
@RequiredArgsConstructor
public class ChatMemoryDomainService implements IChatMemoryDomainService {

    private final IChatMemoryConfigDomainRepository chatMemoryConfigDomainRepository;

    @Override
    public void bindMemoryToConfig(ChatMemoryConfig chatMemoryConfig, Long memoryId) {
        // 验证输入参数
        validateBindingParameters(chatMemoryConfig, memoryId);

        // 检查是否已经绑定
        validateNotAlreadyBound(chatMemoryConfig.getId(), memoryId);

        // 创建绑定关系、触发领域事件并保存聚合根 - 通过领域仓储统一管理
        chatMemoryConfigDomainRepository.createMemoryBinding(chatMemoryConfig, memoryId);

        log.info("成功绑定记忆配置，configId: {}, memoryId: {}", chatMemoryConfig.getId(), memoryId);
    }

    @Override
    public void unbindMemoryFromConfig(ChatMemoryConfig chatMemoryConfig, Long memoryId) {
        // 验证输入参数
        validateUnbindingParameters(chatMemoryConfig, memoryId);

        // 检查绑定关系是否存在
        validateBindingExists(chatMemoryConfig.getId(), memoryId);

        // 删除绑定关系、触发领域事件并保存聚合根 - 通过领域仓储统一管理
        chatMemoryConfigDomainRepository.removeMemoryBinding(chatMemoryConfig, memoryId);

        log.info("成功解绑记忆配置，configId: {}, memoryId: {}", chatMemoryConfig.getId(), memoryId);
    }

    /**
     * 验证绑定操作的参数
     */
    private void validateBindingParameters(ChatMemoryConfig chatMemoryConfig, Long memoryId) {
        Assert.notNull(chatMemoryConfig, "聊天记忆配置不能为空");
        Assert.notNull(chatMemoryConfig.getId(), "聊天记忆配置ID不能为空");
        Assert.notNull(memoryId, "记忆ID不能为空");
        Assert.isTrue(memoryId > 0, "记忆ID必须为正数");

        // 验证聚合根的不变性
        chatMemoryConfig.validateInvariants();
    }

    /**
     * 验证解绑操作的参数
     */
    private void validateUnbindingParameters(ChatMemoryConfig chatMemoryConfig, Long memoryId) {
        Assert.notNull(chatMemoryConfig, "聊天记忆配置不能为空");
        Assert.notNull(chatMemoryConfig.getId(), "聊天记忆配置ID不能为空");
        Assert.notNull(memoryId, "记忆ID不能为空");
        Assert.isTrue(memoryId > 0, "记忆ID必须为正数");
    }

    /**
     * 验证绑定关系不存在
     */
    private void validateNotAlreadyBound(Long configId, Long memoryId) {
        boolean isAlreadyBound = chatMemoryConfigDomainRepository.isMemoryBound(configId, memoryId);
        Assert.isTrue(!isAlreadyBound, "记忆配置已经绑定到该记忆实例，configId: " + configId + ", memoryId: " + memoryId);
    }

    /**
     * 验证绑定关系存在
     */
    private void validateBindingExists(Long configId, Long memoryId) {
        boolean bindingExists = chatMemoryConfigDomainRepository.isMemoryBound(configId, memoryId);
        Assert.isTrue(bindingExists, "绑定关系不存在，无法解绑，configId: " + configId + ", memoryId: " + memoryId);
    }
}