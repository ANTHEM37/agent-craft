package io.github.anthem37.craft.infrastructure.memory.assembler.persistence;


import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hb28301
 * @since 2025/10/11 15:46:24
 */
@Mapper
public interface ChatMemoryConfigPersistenceAssembler {

    ChatMemoryConfigPersistenceAssembler INSTANCE = Mappers.getMapper(ChatMemoryConfigPersistenceAssembler.class);

    ChatMemoryConfig toDomain(ChatMemoryConfigPO po);


    ChatMemoryConfigPO toPO(ChatMemoryConfig aggregate);


}
