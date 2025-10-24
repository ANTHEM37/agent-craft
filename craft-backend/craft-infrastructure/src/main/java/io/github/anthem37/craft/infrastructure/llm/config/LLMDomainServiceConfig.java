package io.github.anthem37.craft.infrastructure.llm.config;

import io.github.anthem37.craft.domain.llm.service.ILLMConfigDomainService;
import io.github.anthem37.craft.domain.llm.service.impl.LLMConfigDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LLM领域服务配置
 *
 * @author hb28301
 * @since 2025/10/20 19:17:29
 */
@Configuration
public class LLMDomainServiceConfig {


    @Bean
    public ILLMConfigDomainService llmConfigDomainService() {
        return new LLMConfigDomainService();
    }

}
