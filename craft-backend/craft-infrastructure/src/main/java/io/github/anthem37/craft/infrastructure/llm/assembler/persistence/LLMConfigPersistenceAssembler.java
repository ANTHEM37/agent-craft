package io.github.anthem37.craft.infrastructure.llm.assembler.persistence;


import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.infrastructure.llm.mybatis.po.LLMConfigPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hb28301
 * @since 2025/10/11 15:46:24
 */
@Mapper
public interface LLMConfigPersistenceAssembler {

    LLMConfigPersistenceAssembler INSTANCE = Mappers.getMapper(LLMConfigPersistenceAssembler.class);

    LLMConfig toDomain(LLMConfigPO po);


    LLMConfigPO toPO(LLMConfig aggregate);


}
