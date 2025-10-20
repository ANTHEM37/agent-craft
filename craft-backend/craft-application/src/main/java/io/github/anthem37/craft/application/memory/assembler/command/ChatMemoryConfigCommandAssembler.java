package io.github.anthem37.craft.application.memory.assembler.command;

import io.github.anthem37.craft.application.memory.cqrs.command.CreateChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.cqrs.command.UpdateChatMemoryConfigCommand;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hb28301
 * @since 2025/10/11 15:46:24
 */
@Mapper
public interface ChatMemoryConfigCommandAssembler {

    ChatMemoryConfigCommandAssembler INSTANCE = Mappers.getMapper(ChatMemoryConfigCommandAssembler.class);


    ChatMemoryConfig toDomain(CreateChatMemoryConfigCommand command);

    ChatMemoryConfig toDomain(UpdateChatMemoryConfigCommand command);
}
