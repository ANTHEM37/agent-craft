package io.github.anthem37.craft.domain.llm.event;

import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.easy.ddd.domain.event.IDomainEvent;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hb28301
 * @since 2025/10/11 16:04:00
 */
@Data
public class CreatedLLMConfigEvent implements IDomainEvent<Long> {

    /**
     * 事件类型，标识事件的名称
     */
    public final String eventType = "CreatedLLMConfigEvent";

    /**
     * 聚合根ID，标识创建的LLM配置
     */
    private Long aggregateId;

    /**
     * 事件数据，包含创建的LLM配置的详细信息
     */
    private Map<String, Object> eventData = new HashMap<>();

    public CreatedLLMConfigEvent(LLMConfig llmConfig) {
        this.aggregateId = llmConfig.getId();
        eventData.put("llmConfig", llmConfig);
    }
}
