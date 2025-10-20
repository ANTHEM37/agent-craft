package io.github.anthem37.craft.application.memory.service;

import dev.langchain4j.memory.ChatMemory;
import io.github.anthem37.craft.application.memory.cqrs.command.CreateChatMemoryCommand;

/**
 * 聊天记忆创建服务接口
 * 负责协调ChatMemory的创建和绑定流程
 *
 * @author hb28301
 * @since 2025/01/15
 */
public interface IChatMemoryService {

    /**
     * 创建聊天记忆实例
     * 该方法会：
     * 1. 根据配置ID查询记忆配置
     * 2. 生成唯一的memoryId
     * 3. 调用领域服务进行绑定
     * 4. 调用工厂创建ChatMemory实例
     *
     * @param command 创建聊天记忆命令
     * @return 创建的聊天记忆实例
     */
    ChatMemory createChatMemory(CreateChatMemoryCommand command);
}