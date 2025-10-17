package io.github.anthem37.craft.infrastructure.memory.domain.respository.Impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.respository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.craft.infrastructure.memory.converter.ChatMemoryConfigPOConverter;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigRefMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigPO;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigRefPO;
import io.github.anthem37.easy.ddd.infrastructure.repository.AbstractDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author hb28301
 * @since 2025/10/16 14:47:05
 */
@Component
@RequiredArgsConstructor
public class ChatMemoryConfigDomainRepository extends AbstractDomainRepository<ChatMemoryConfig, Long> implements IChatMemoryConfigDomainRepository {

    private final IChatMemoryConfigMapper chatMemoryConfigMapper;
    private final IChatMemoryConfigRefMapper chatMemoryConfigRefMapper;

    @Override
    protected Optional<ChatMemoryConfig> doFindById(Long id) {
        return Optional.ofNullable(chatMemoryConfigMapper.selectById(id)).map(ChatMemoryConfigPOConverter.INSTANCE::toDomain);
    }

    @Override
    protected void doInsert(ChatMemoryConfig chatMemoryConfig) {
        ChatMemoryConfigPO po = ChatMemoryConfigPOConverter.INSTANCE.toPO(chatMemoryConfig);
        chatMemoryConfigMapper.insert(po);
        chatMemoryConfig.setId(po.getId());
        chatMemoryConfig.markAsCreated();
    }

    @Override
    protected void doUpdateById(ChatMemoryConfig chatMemoryConfig) {
        ChatMemoryConfigPO po = ChatMemoryConfigPOConverter.INSTANCE.toPO(chatMemoryConfig);
        chatMemoryConfigMapper.updateById(po);
        chatMemoryConfig.markAsUpdated();
    }

    @Override
    protected void doDeleteById(ChatMemoryConfig chatMemoryConfig) {
        chatMemoryConfigMapper.deleteById(chatMemoryConfig.getId());
        chatMemoryConfig.markAsDeleted();
    }

    @Override
    public boolean isMemoryBound(Long configId, Long memoryId) {
        LambdaQueryWrapper<ChatMemoryConfigRefPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatMemoryConfigRefPO::getConfigId, configId)
                .eq(ChatMemoryConfigRefPO::getMemoryId, memoryId);
        return chatMemoryConfigRefMapper.selectCount(queryWrapper) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createMemoryBinding(Long configId, Long memoryId) {
        Optional<ChatMemoryConfig> chatMemoryConfig = doFindById(configId);
        Assert.isTrue(chatMemoryConfig.isPresent(), "记忆配置不存在，无法创建绑定关系");
        createMemoryBinding(chatMemoryConfig.get(), memoryId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createMemoryBinding(ChatMemoryConfig chatMemoryConfig, Long memoryId) {
        // 创建绑定关系
        ChatMemoryConfigRefPO refPO = new ChatMemoryConfigRefPO()
                .setConfigId(chatMemoryConfig.getId())
                .setMemoryId(memoryId);
        chatMemoryConfigRefMapper.insert(refPO);

        // 触发领域事件
        chatMemoryConfig.markAsBindMemory(memoryId);

        // 保存聚合根（会自动发布领域事件）
        save(chatMemoryConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeMemoryBinding(Long configId, Long memoryId) {
        Optional<ChatMemoryConfig> chatMemoryConfig = doFindById(configId);
        Assert.isTrue(chatMemoryConfig.isPresent(), "记忆配置不存在，无法创建绑定关系");
        removeMemoryBinding(chatMemoryConfig.get(), memoryId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeMemoryBinding(ChatMemoryConfig chatMemoryConfig, Long memoryId) {
        // 删除绑定关系
        LambdaQueryWrapper<ChatMemoryConfigRefPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatMemoryConfigRefPO::getConfigId, chatMemoryConfig.getId())
                .eq(ChatMemoryConfigRefPO::getMemoryId, memoryId);
        int deletedCount = chatMemoryConfigRefMapper.delete(queryWrapper);

        if (deletedCount > 0) {
            // 触发领域事件
            chatMemoryConfig.markAsUnbindMemory(memoryId);

            // 保存聚合根（会自动发布领域事件）
            save(chatMemoryConfig);
        }
    }

}
