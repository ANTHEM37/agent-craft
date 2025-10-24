package io.github.anthem37.craft.domain.tool.event;

import io.github.anthem37.craft.domain.tool.model.entity.ToolConfig;
import io.github.anthem37.easy.ddd.domain.event.IDomainEvent;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建工具配置领域事件
 *
 * @author hb28301
 */
@Data
public class CreatedToolConfigEvent implements IDomainEvent {

    /**
     * 事件类型
     */
    public final String eventType = "CreatedToolConfigEvent";

    /**
     * 聚合根ID
     */
    private Long aggregateId;

    /**
     * 事件数据
     */
    private Map<String, Object> eventData = new HashMap<>();

    public CreatedToolConfigEvent(ToolConfig toolConfig) {
        this.aggregateId = toolConfig.getId();
        eventData.put("toolConfig", toolConfig);
    }
}