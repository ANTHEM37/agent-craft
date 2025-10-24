package io.github.anthem37.craft.infrastructure.tool.assembler.persistence;

import io.github.anthem37.craft.domain.tool.model.entity.ToolConfig;
import io.github.anthem37.craft.infrastructure.tool.mybatis.po.ToolConfigPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ToolConfig 持久化装配器
 */
@Mapper
public interface ToolConfigPersistenceAssembler {

    ToolConfigPersistenceAssembler INSTANCE = Mappers.getMapper(ToolConfigPersistenceAssembler.class);

    @Mapping(source = "params", target = "paramsSchema")
    @Mapping(source = "result", target = "resultSchema")
    ToolConfig toDomain(ToolConfigPO po);

    @Mapping(source = "paramsSchema", target = "params")
    @Mapping(source = "resultSchema", target = "result")
    ToolConfigPO toPO(ToolConfig aggregate);
}