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
public class DeletedChatMemoryConfigEvent implements IDomainEvent<Long> {

    /**
     * 事件类型，标识事件的名称
     */
    public final String eventType = "DeletedChatMemoryConfigEvent";

    /**
     * 聚合根ID，标识删除的记忆配置
     */
    private Long aggregateId;

    /**
     * 事件数据，包含创建的记忆配置的详细信息
     */
    private Map<String, Object> eventData = new HashMap<>();

    public DeletedChatMemoryConfigEvent(ChatMemoryConfig chatMemoryConfig) {
        this.aggregateId = chatMemoryConfig.getId();
        eventData.put("chatMemoryConfig", chatMemoryConfig);
    }
}
