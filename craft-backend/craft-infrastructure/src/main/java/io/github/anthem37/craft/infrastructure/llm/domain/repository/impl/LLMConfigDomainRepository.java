package io.github.anthem37.craft.infrastructure.llm.domain.repository.impl;

import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.domain.llm.repository.ILLMConfigDomainRepository;
import io.github.anthem37.craft.infrastructure.llm.converter.LLMConfigPOConverter;
import io.github.anthem37.craft.infrastructure.llm.mybatis.mapper.ILLMConfigMapper;
import io.github.anthem37.craft.infrastructure.llm.mybatis.po.LLMConfigPO;
import io.github.anthem37.easy.ddd.infrastructure.repository.AbstractDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * LLM配置领域仓库实现类
 *
 * @author hb28301
 * @since 2025/10/11 15:34:28
 */
@Component
@RequiredArgsConstructor
public class LLMConfigDomainRepository extends AbstractDomainRepository<LLMConfig, Long> implements ILLMConfigDomainRepository {

    private final ILLMConfigMapper llmConfigMapper;

    @Override
    protected Optional<LLMConfig> doFindById(Long id) {
        LLMConfig llmConfig = LLMConfigPOConverter.INSTANCE.toDomain(llmConfigMapper.selectById(id));

        return Optional.ofNullable(llmConfig);
    }

    @Override
    protected void doInsert(LLMConfig aggregate) {
        LLMConfigPO po = LLMConfigPOConverter.INSTANCE.toPO(aggregate);
        llmConfigMapper.insert(po);
        aggregate.setId(po.getId());
        aggregate.markAsCreated();
    }

    @Override
    protected void doUpdateById(LLMConfig aggregate) {
        LLMConfigPO po = LLMConfigPOConverter.INSTANCE.toPO(aggregate);
        llmConfigMapper.updateById(po);
        aggregate.markAsUpdated();
    }

    @Override
    protected void doDeleteById(LLMConfig aggregate) {
        LLMConfigPO po = LLMConfigPOConverter.INSTANCE.toPO(aggregate);
        llmConfigMapper.deleteById(po.getId());
        aggregate.markAsDeleted();
    }

}
