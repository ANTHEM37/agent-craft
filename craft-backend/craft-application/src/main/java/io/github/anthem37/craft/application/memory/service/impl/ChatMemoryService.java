package io.github.anthem37.craft.application.memory.service.impl;

import cn.hutool.core.util.IdUtil;
import dev.langchain4j.memory.ChatMemory;
import io.github.anthem37.craft.application.memory.service.IChatMemoryService;
import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.craft.domain.memory.model.factory.IChatMemoryFactory;
import io.github.anthem37.craft.domain.memory.service.IChatMemoryBindingDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 聊天记忆创建服务实现
 * 负责协调ChatMemory的创建和绑定流程
 * 
 * @author hb28301
 * @since 2025/01/15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMemoryService implements IChatMemoryService {
    
    private final IChatMemoryBindingDomainService chatMemoryBindingDomainService;
    private final IChatMemoryFactory chatMemoryFactory;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ChatMemory createChatMemory(ChatMemoryConfig memoryConfig) {
        log.info("开始创建聊天记忆，配置ID: {}", memoryConfig.getId());
        
        // 1. 生成唯一的memoryId
        long memoryId = IdUtil.getSnowflake().nextId();
        log.debug("生成memoryId: {}", memoryId);
        
        // 2. 调用领域服务进行绑定
        chatMemoryBindingDomainService.bindMemoryToConfig(memoryConfig, memoryId);
        log.debug("完成记忆配置绑定，configId: {}, memoryId: {}", memoryConfig.getId(), memoryId);
        
        // 3. 调用工厂创建ChatMemory实例
        ChatMemory chatMemory = chatMemoryFactory.createChatMemory(memoryConfig, memoryId);
        log.info("成功创建聊天记忆，memoryId: {}, 类型: {}", memoryId, memoryConfig.getChatMemoryType());
        
        return chatMemory;
    }
}