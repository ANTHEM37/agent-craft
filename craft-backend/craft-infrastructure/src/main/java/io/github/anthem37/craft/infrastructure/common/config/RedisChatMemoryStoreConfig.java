package io.github.anthem37.craft.infrastructure.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hb28301
 * @date 2025/10/17 11:44:20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redis.chat-memory-store")
public class RedisChatMemoryStoreConfig {

    /**
     * 初始过期时间（秒）
     */
    private long initialExpire = 30 * 60;

    /**
     * 续约时间（秒）
     */
    private long renewExpire = 30 * 60;
}
