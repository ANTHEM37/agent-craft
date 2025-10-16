package io.github.anthem37.craft.infrastructure.memory.domain.respository.Impl;

import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.respository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.craft.infrastructure.memory.converter.ChatMemoryConfigPOConverter;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author hb28301
 * @since 2025/10/16 14:47:05
 */
@Component
@RequiredArgsConstructor
public class ChatMemoryConfigDomainRepository implements IChatMemoryConfigDomainRepository {

    private final IChatMemoryConfigMapper chatMemoryConfigMapper;

    @Override
    public Optional<ChatMemoryConfig> findById(Long id) {
        return Optional.ofNullable(chatMemoryConfigMapper.selectById(id)).map(ChatMemoryConfigPOConverter.INSTANCE::toDomain);
    }

    @Override
    public void save(ChatMemoryConfig chatMemoryConfig) {
        ChatMemoryConfigPO po = ChatMemoryConfigPOConverter.INSTANCE.toPO(chatMemoryConfig);
        chatMemoryConfigMapper.insert(po);
        chatMemoryConfig.setId(po.getId());
        chatMemoryConfig.markAsCreated();
    }

    @Override
    public void update(ChatMemoryConfig chatMemoryConfig) {
        ChatMemoryConfigPO po = ChatMemoryConfigPOConverter.INSTANCE.toPO(chatMemoryConfig);
        chatMemoryConfigMapper.updateById(po);
        chatMemoryConfig.markAsUpdated();
    }

    @Override
    public void remove(ChatMemoryConfig chatMemoryConfig) {
        chatMemoryConfigMapper.deleteById(chatMemoryConfig.getId());
        chatMemoryConfig.markAsDeleted();
    }

}
