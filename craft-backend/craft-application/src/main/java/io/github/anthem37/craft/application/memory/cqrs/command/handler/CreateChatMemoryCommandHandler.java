package io.github.anthem37.craft.application.memory.cqrs.command.handler;

import cn.hutool.core.util.IdUtil;
import dev.langchain4j.memory.ChatMemory;
import io.github.anthem37.craft.application.memory.cqrs.command.CreateChatMemoryCommand;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.model.factory.IChatMemoryFactory;
import io.github.anthem37.craft.domain.memory.repository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.craft.domain.memory.service.IChatMemoryDomainService;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author hb28301
 * @since 2025/10/17 17:07:01
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateChatMemoryCommandHandler implements ICommandHandler<CreateChatMemoryCommand, ChatMemory> {

    private final IChatMemoryConfigDomainRepository chatMemoryConfigDomainRepository;
    private final IChatMemoryDomainService chatMemoryDomainService;
    private final IChatMemoryFactory chatMemoryFactory;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ChatMemory handle(CreateChatMemoryCommand command) {
        Optional<ChatMemoryConfig> chatMemoryConfigOptional = chatMemoryConfigDomainRepository.findById(command.getConfigId());
        Assert.isTrue(chatMemoryConfigOptional.isPresent(), "记忆配置不存在，配置ID: " + command.getConfigId());
        ChatMemoryConfig memoryConfig = chatMemoryConfigOptional.get();
        long memoryId = IdUtil.getSnowflake().nextId();
        // 发布绑定记忆事件
        memoryConfig.markAsBindMemory(memoryId);
        chatMemoryConfigDomainRepository.save(memoryConfig);

        return chatMemoryFactory.createChatMemory(memoryConfig, memoryId);
    }

    @Override
    public Class<CreateChatMemoryCommand> getSupportedCommandType() {
        return CreateChatMemoryCommand.class;
    }
}
