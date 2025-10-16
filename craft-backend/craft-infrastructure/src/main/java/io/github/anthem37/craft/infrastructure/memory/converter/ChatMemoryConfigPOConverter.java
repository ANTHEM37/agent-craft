package io.github.anthem37.craft.infrastructure.memory.converter;

import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hb28301
 * @since 2025/10/11 15:46:24
 */
@Mapper
public interface ChatMemoryConfigPOConverter {

    ChatMemoryConfigPOConverter INSTANCE = Mappers.getMapper(ChatMemoryConfigPOConverter.class);

    ChatMemoryConfig toDomain(ChatMemoryConfigPO po);


    ChatMemoryConfigDTO toDTO(ChatMemoryConfigPO po);

    ChatMemoryConfigPO toPO(ChatMemoryConfig aggregate);

    ChatMemoryConfigPO toPO(ChatMemoryConfigDTO dto);
}
