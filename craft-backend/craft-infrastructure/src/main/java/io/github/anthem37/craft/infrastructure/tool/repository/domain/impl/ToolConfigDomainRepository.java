package io.github.anthem37.craft.infrastructure.tool.repository.domain.impl;

import io.github.anthem37.craft.domain.tool.model.entity.ToolConfig;
import io.github.anthem37.craft.domain.tool.repository.IToolConfigDomainRepository;
import io.github.anthem37.craft.infrastructure.tool.assembler.persistence.ToolConfigPersistenceAssembler;
import io.github.anthem37.craft.infrastructure.tool.mybatis.mapper.IToolConfigMapper;
import io.github.anthem37.craft.infrastructure.tool.mybatis.po.ToolConfigPO;
import io.github.anthem37.easy.ddd.infrastructure.repository.AbstractDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * ToolConfig 领域仓储实现
 */
@Component
@RequiredArgsConstructor
public class ToolConfigDomainRepository extends AbstractDomainRepository<ToolConfig, Long> implements IToolConfigDomainRepository {

    private final IToolConfigMapper toolConfigMapper;

    @Override
    protected Optional<ToolConfig> doFindById(Long id) {
        return Optional.ofNullable(toolConfigMapper.selectById(id))
                .map(ToolConfigPersistenceAssembler.INSTANCE::toDomain);
    }

    @Override
    protected void doInsert(ToolConfig toolConfig) {
        ToolConfigPO po = ToolConfigPersistenceAssembler.INSTANCE.toPO(toolConfig);
        toolConfigMapper.insert(po);
        toolConfig.setId(po.getId());
        toolConfig.markAsCreated();
    }

    @Override
    protected void doUpdateById(ToolConfig toolConfig) {
        ToolConfigPO po = ToolConfigPersistenceAssembler.INSTANCE.toPO(toolConfig);
        toolConfigMapper.updateById(po);
        toolConfig.markAsUpdated();
    }

    @Override
    protected void doDeleteById(ToolConfig toolConfig) {
        toolConfigMapper.deleteById(toolConfig.getId());
        toolConfig.markAsDeleted();
    }
}