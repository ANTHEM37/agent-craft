package io.github.anthem37.craft.domain.tool.event;

import io.github.anthem37.craft.domain.tool.model.entity.ToolConfig;
import io.github.anthem37.easy.ddd.domain.event.IDomainEvent;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除工具配置领域事件
 *
 * @author hb28301
 */
@Data
public class DeletedToolConfigEvent implements IDomainEvent {

    public final String eventType = "DeletedToolConfigEvent";

    private Long aggregateId;

    private Map<String, Object> eventData = new HashMap<>();

    public DeletedToolConfigEvent(ToolConfig toolConfig) {
        this.aggregateId = toolConfig.getId();
        eventData.put("toolConfig", toolConfig);
    }
}