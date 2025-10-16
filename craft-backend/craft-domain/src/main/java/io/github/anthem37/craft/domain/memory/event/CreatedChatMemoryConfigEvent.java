package io.github.anthem37.craft.domain.memory.event;

import io.github.anthem37.craft.domain.memory.model.entity.ChatMemoryConfig;
import io.github.anthem37.easy.ddd.domain.event.IDomainEvent;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hb28301
 * @since 2025/10/11 16:04:00
 */
@Data
public class CreatedChatMemoryConfigEvent implements IDomainEvent {

    /**
     * 事件类型，标识事件的名称
     */
    public final String eventType = "CreatedChatMemoryConfigEvent";

    /**
     * 聚合根ID，标识创建的记忆配置
     */
    private Long aggregateId;

    /**
     * 事件数据，包含创建的LLM配置的详细信息
     */
    private Map<String, Object> eventData = new HashMap<>();

    public CreatedChatMemoryConfigEvent(ChatMemoryConfig ChatMemoryConfig) {
        this.aggregateId = ChatMemoryConfig.getId();
        eventData.put("chatMemoryConfig", ChatMemoryConfig);
    }
}
