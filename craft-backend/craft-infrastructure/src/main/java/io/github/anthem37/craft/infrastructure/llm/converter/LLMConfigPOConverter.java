package io.github.anthem37.craft.infrastructure.llm.converter;

import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.infrastructure.llm.mybatis.po.LLMConfigPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hb28301
 * @since 2025/10/11 15:46:24
 */
@Mapper
public interface LLMConfigPOConverter {

    LLMConfigPOConverter INSTANCE = Mappers.getMapper(LLMConfigPOConverter.class);

    LLMConfig toDomain(LLMConfigPO po);


    LLMConfigDTO toDTO(LLMConfigPO po);

    LLMConfigPO toPO(LLMConfig aggregate);

    LLMConfigPO toPO(LLMConfigDTO aggregate);
}
