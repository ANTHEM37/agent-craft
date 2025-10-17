package io.github.anthem37.craft.domain.memory.event;

import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.easy.ddd.domain.event.IDomainEvent;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hb28301
 * @date 2025/10/17 14:52:17
 */
@Data
public class BindMemoryChatMemoryConfigEvent implements IDomainEvent {
    /**
     * 事件类型，标识事件的名称
     */
    public final String eventType = "BindMemoryChatMemoryConfigEvent";

    /**
     * 聚合根ID，标识创建的记忆配置
     */
    private Long aggregateId;

    /**
     * 记忆ID，标识绑定的记忆
     */
    private Long memoryId;

    /**
     * 事件数据，包含创建的LLM配置的详细信息
     */
    private Map<String, Object> eventData = new HashMap<>();

    public BindMemoryChatMemoryConfigEvent(ChatMemoryConfig ChatMemoryConfig, Long memoryId) {
        this.aggregateId = ChatMemoryConfig.getId();
        this.memoryId = memoryId;
        eventData.put("chatMemoryConfig", ChatMemoryConfig);
        eventData.put("memoryId", memoryId);
    }
}
