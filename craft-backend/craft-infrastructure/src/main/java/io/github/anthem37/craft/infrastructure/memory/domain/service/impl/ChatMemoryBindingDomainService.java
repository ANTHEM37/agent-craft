package io.github.anthem37.craft.infrastructure.memory.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.respository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.craft.domain.memory.service.IChatMemoryBindingDomainService;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigRefMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigRefPO;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 聊天记忆绑定领域服务实现
 *
 * @author hb28301
 * @since 2025/10/17 15:00:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMemoryBindingDomainService implements IChatMemoryBindingDomainService {

    private final IChatMemoryConfigRefMapper chatMemoryConfigRefMapper;
    private final IChatMemoryConfigDomainRepository chatMemoryConfigDomainRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindMemoryToConfig(ChatMemoryConfig chatMemoryConfig, Long memoryId) {
        // 验证输入参数
        validateBindingParameters(chatMemoryConfig, memoryId);
        
        // 检查是否已经绑定
        validateNotAlreadyBound(chatMemoryConfig.getId(), memoryId);

        // 创建绑定关系
        ChatMemoryConfigRefPO refPO = new ChatMemoryConfigRefPO()
                .setConfigId(chatMemoryConfig.getId())
                .setMemoryId(memoryId);
        chatMemoryConfigRefMapper.insert(refPO);

        // 触发领域事件
        chatMemoryConfig.markAsBindMemory(memoryId);

        // 保存聚合根
        chatMemoryConfigDomainRepository.save(chatMemoryConfig);
        
        log.info("成功绑定记忆配置，configId: {}, memoryId: {}", chatMemoryConfig.getId(), memoryId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unbindMemoryFromConfig(ChatMemoryConfig chatMemoryConfig, Long memoryId) {
        // 验证输入参数
        validateUnbindingParameters(chatMemoryConfig, memoryId);
        
        // 检查绑定关系是否存在
        validateBindingExists(chatMemoryConfig.getId(), memoryId);
        
        // 删除绑定关系
        LambdaQueryWrapper<ChatMemoryConfigRefPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatMemoryConfigRefPO::getConfigId, chatMemoryConfig.getId())
                .eq(ChatMemoryConfigRefPO::getMemoryId, memoryId);

        int deletedCount = chatMemoryConfigRefMapper.delete(queryWrapper);
        if (deletedCount == 0) {
            throw new IllegalStateException("解绑失败，未找到对应的绑定关系");
        }

        // 触发解绑事件
        chatMemoryConfig.markAsUnbindMemory(memoryId);

        // 保存聚合根
        chatMemoryConfigDomainRepository.save(chatMemoryConfig);
        
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
        LambdaQueryWrapper<ChatMemoryConfigRefPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatMemoryConfigRefPO::getConfigId, configId)
                .eq(ChatMemoryConfigRefPO::getMemoryId, memoryId);

        ChatMemoryConfigRefPO existingRef = chatMemoryConfigRefMapper.selectOne(queryWrapper);
        Assert.isTrue(existingRef == null, 
            "记忆配置已经绑定到该记忆实例，configId: " + configId + ", memoryId: " + memoryId);
    }
    
    /**
     * 验证绑定关系存在
     */
    private void validateBindingExists(Long configId, Long memoryId) {
        LambdaQueryWrapper<ChatMemoryConfigRefPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatMemoryConfigRefPO::getConfigId, configId)
                .eq(ChatMemoryConfigRefPO::getMemoryId, memoryId);

        ChatMemoryConfigRefPO existingRef = chatMemoryConfigRefMapper.selectOne(queryWrapper);
        Assert.isTrue(existingRef != null, 
            "绑定关系不存在，无法解绑，configId: " + configId + ", memoryId: " + memoryId);
    }
}