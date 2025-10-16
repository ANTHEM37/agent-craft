package io.github.anthem37.craft.infrastructure.memory.domain.respository.Impl;

import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.respository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.craft.infrastructure.memory.converter.IChatMemoryConfigPOConverter;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigMapper;
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
        return Optional.ofNullable(chatMemoryConfigMapper.selectById(id))
                .map(IChatMemoryConfigPOConverter.INSTANCE::toDomain);
    }

    @Override
    public void save(ChatMemoryConfig chatMemoryConfig) {
        chatMemoryConfigMapper.insert(IChatMemoryConfigPOConverter.INSTANCE.toPO(chatMemoryConfig));
    }

    @Override
    public void update(ChatMemoryConfig chatMemoryConfig) {
        chatMemoryConfigMapper.updateById(IChatMemoryConfigPOConverter.INSTANCE.toPO(chatMemoryConfig));
    }

    @Override
    public void remove(ChatMemoryConfig chatMemoryConfig) {
        chatMemoryConfigMapper.deleteById(chatMemoryConfig.getId());
    }

}
