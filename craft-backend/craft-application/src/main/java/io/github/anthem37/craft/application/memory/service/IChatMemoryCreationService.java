package io.github.anthem37.craft.application.memory.service;

import dev.langchain4j.memory.ChatMemory;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;

/**
 * 聊天记忆创建服务接口
 * 负责协调ChatMemory的创建和绑定流程
 * 
 * @author hb28301
 * @since 2025/01/15
 */
public interface IChatMemoryCreationService {
    
    /**
     * 创建聊天记忆实例
     * 该方法会：
     * 1. 生成唯一的memoryId
     * 2. 调用领域服务进行绑定
     * 3. 调用工厂创建ChatMemory实例
     * 
     * @param memoryConfig 聊天记忆配置
     * @return 创建的聊天记忆实例
     */
    ChatMemory createChatMemory(ChatMemoryConfig memoryConfig);
}