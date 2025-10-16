package io.github.anthem37.craft.application.memory.converter;

import io.github.anthem37.craft.application.memory.dto.command.CreateChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.dto.command.UpdateChatMemoryConfigCommand;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hb28301
 * @since 2025/10/11 15:46:24
 */
@Mapper
public interface ChatMemoryConfigCommandConverter {

    ChatMemoryConfigCommandConverter INSTANCE = Mappers.getMapper(ChatMemoryConfigCommandConverter.class);


    ChatMemoryConfig toDomain(CreateChatMemoryConfigCommand command);

    ChatMemoryConfig toDomain(UpdateChatMemoryConfigCommand command);
}
