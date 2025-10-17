package io.github.anthem37.craft.infrastructure.memory.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.respository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.craft.domain.memory.service.IChatMemoryBindingDomainService;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigRefMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigRefPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 聊天记忆绑定领域服务实现
 *
 * @author hb28301
 * @since 2025/10/17 15:00:00
 */
@Service
@RequiredArgsConstructor
public class ChatMemoryBindingDomainService implements IChatMemoryBindingDomainService {

    private final IChatMemoryConfigRefMapper chatMemoryConfigRefMapper;
    private final IChatMemoryConfigDomainRepository chatMemoryConfigDomainRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindMemoryToConfig(ChatMemoryConfig chatMemoryConfig, Long memoryId) {
        // 检查是否已经绑定
        LambdaQueryWrapper<ChatMemoryConfigRefPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatMemoryConfigRefPO::getConfigId, chatMemoryConfig.getId())
                .eq(ChatMemoryConfigRefPO::getMemoryId, memoryId);

        ChatMemoryConfigRefPO existingRef = chatMemoryConfigRefMapper.selectOne(queryWrapper);
        if (existingRef != null) {
            throw new IllegalStateException("记忆配置已经绑定到该记忆实例");
        }

        // 创建绑定关系
        ChatMemoryConfigRefPO refPO = new ChatMemoryConfigRefPO()
                .setConfigId(chatMemoryConfig.getId())
                .setMemoryId(memoryId);
        chatMemoryConfigRefMapper.insert(refPO);

        // 触发领域事件
        chatMemoryConfig.markAsBindMemory(memoryId);

        // 保存聚合根
        chatMemoryConfigDomainRepository.save(chatMemoryConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unbindMemoryFromConfig(ChatMemoryConfig chatMemoryConfig, Long memoryId) {
        // 删除绑定关系
        LambdaQueryWrapper<ChatMemoryConfigRefPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatMemoryConfigRefPO::getConfigId, chatMemoryConfig.getId())
                .eq(ChatMemoryConfigRefPO::getMemoryId, memoryId);

        chatMemoryConfigRefMapper.delete(queryWrapper);

        // 这里可以触发解绑事件，如果需要的话
        chatMemoryConfig.markAsUnbindMemory(memoryId);

        // 保存聚合根
        chatMemoryConfigDomainRepository.save(chatMemoryConfig);
    }
}