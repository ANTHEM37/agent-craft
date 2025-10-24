package io.github.anthem37.craft.domain.tool.event;

import io.github.anthem37.craft.domain.tool.model.entity.ToolConfig;
import io.github.anthem37.easy.ddd.domain.event.IDomainEvent;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新工具配置领域事件
 *
 * @author hb28301
 */
@Data
public class UpdatedToolConfigEvent implements IDomainEvent {

    public final String eventType = "UpdatedToolConfigEvent";

    private Long aggregateId;

    private Map<String, Object> eventData = new HashMap<>();

    public UpdatedToolConfigEvent(ToolConfig toolConfig) {
        this.aggregateId = toolConfig.getId();
        eventData.put("toolConfig", toolConfig);
    }
}