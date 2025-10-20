package io.github.anthem37.craft.infrastructure.memory.config;

import io.github.anthem37.craft.domain.memory.repository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.craft.domain.memory.service.impl.ChatMemoryDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 记忆领域服务配置
 *
 * @author hb28301
 * @since 2025/10/17 15:00:00
 */
@Configuration
public class MemoryDomainServiceConfig {

    @Bean
    public ChatMemoryDomainService chatMemoryDomainService(IChatMemoryConfigDomainRepository chatMemoryConfigDomainRepository) {
        return new ChatMemoryDomainService(chatMemoryConfigDomainRepository);
    }
}