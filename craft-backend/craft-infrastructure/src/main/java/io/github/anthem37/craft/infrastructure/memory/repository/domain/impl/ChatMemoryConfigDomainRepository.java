package io.github.anthem37.craft.infrastructure.memory.repository.domain.impl;

import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.repository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.craft.infrastructure.memory.assembler.persistence.ChatMemoryConfigPersistenceAssembler;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigRefMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigPO;
import io.github.anthem37.easy.ddd.infrastructure.repository.AbstractDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        return Optional.ofNullable(chatMemoryConfigMapper.selectById(id)).map(ChatMemoryConfigPersistenceAssembler.INSTANCE::toDomain);
    }

    @Override
    protected void doInsert(ChatMemoryConfig chatMemoryConfig) {
        ChatMemoryConfigPO po = ChatMemoryConfigPersistenceAssembler.INSTANCE.toPO(chatMemoryConfig);
        chatMemoryConfigMapper.insert(po);
        chatMemoryConfig.setId(po.getId());
        chatMemoryConfig.markAsCreated();
    }

    @Override
    protected void doUpdateById(ChatMemoryConfig chatMemoryConfig) {
        ChatMemoryConfigPO po = ChatMemoryConfigPersistenceAssembler.INSTANCE.toPO(chatMemoryConfig);
        chatMemoryConfigMapper.updateById(po);
        chatMemoryConfig.markAsUpdated();
    }

    @Override
    protected void doDeleteById(ChatMemoryConfig chatMemoryConfig) {
        chatMemoryConfigMapper.deleteById(chatMemoryConfig.getId());
        chatMemoryConfig.markAsDeleted();
    }

}
