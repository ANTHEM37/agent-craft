package io.github.anthem37.craft.domain.llm.model.entity;

import io.github.anthem37.craft.domain.common.model.entity.BaseAggregateRoot;
import io.github.anthem37.craft.domain.llm.event.CreatedLLMConfigEvent;
import io.github.anthem37.craft.domain.llm.event.DeletedLLMConfigEvent;
import io.github.anthem37.craft.domain.llm.event.UpdatedLLMConfigEvent;
import io.github.anthem37.craft.domain.llm.model.value.ExtraInfo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;


/**
 * @author hb28301
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LLMConfig extends BaseAggregateRoot {

    /**
     * 自定义API基础URL，用于指定自定义的OpenAI API地址
     */
    private String baseUrl;

    /**
     * API密钥，用于身份验证
     */
    private String apiKey;

    /**
     * 模型名称，指定要使用的具体模型
     */
    private String modelName;

    /**
     * 额外配置信息，包含模型的详细参数
     */
    @Valid
    private ExtraInfo extraInfo;

    /**
     * 标记为已创建，触发 CreatedLLMConfigEvent 事件
     */
    public void markAsCreated() {
        addDomainEvent(new CreatedLLMConfigEvent(this));
    }

    /**
     * 标记为已更新，触发 UpdatedLLMConfigEvent 事件
     */
    public void markAsUpdated() {
        addDomainEvent(new UpdatedLLMConfigEvent(this));
    }

    /**
     * 标记为已删除，触发 DeletedLLMConfigEvent 事件
     */
    public void markAsDeleted() {
        addDomainEvent(new DeletedLLMConfigEvent(this));
    }
}
