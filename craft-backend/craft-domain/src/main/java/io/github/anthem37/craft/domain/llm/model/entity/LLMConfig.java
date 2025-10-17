package io.github.anthem37.craft.domain.llm.model.entity;

import io.github.anthem37.craft.domain.common.model.entity.BaseAggregateRoot;
import io.github.anthem37.craft.domain.llm.event.CreatedLLMConfigEvent;
import io.github.anthem37.craft.domain.llm.event.DeletedLLMConfigEvent;
import io.github.anthem37.craft.domain.llm.event.UpdatedLLMConfigEvent;
import io.github.anthem37.craft.domain.llm.model.value.ExtraInfo;
import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
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
     * 配置名称，用于标识不同的模型配置
     */
    private String configName;

    /**
     * 模型提供商（比如openAI、dashscope等）
     */
    private LLMProvider provider;

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
     * 模型描述，用于说明模型的功能和特性
     */
    private String description;

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

    /**
     * 验证聚合根的不变性
     *
     * @throws IllegalArgumentException 当不变性被违反时抛出
     */
    public void validateInvariants() {
        validateRequiredFields();
        validateExtraInfo();
        validateProviderAndBaseUrl();
    }

    /**
     * 验证必填字段
     */
    private void validateRequiredFields() {
        Assert.hasText(configName, "配置名称不能为空");
        Assert.notNull(provider, "模型提供商不能为空");
        Assert.hasText(apiKey, "API密钥不能为空");
        Assert.hasText(modelName, "模型名称不能为空");
    }

    /**
     * 验证额外配置信息
     */
    private void validateExtraInfo() {
        if (extraInfo != null) {
            extraInfo.validate();
        }
    }

    /**
     * 验证提供商和基础URL的一致性
     */
    private void validateProviderAndBaseUrl() {
        Assert.isTrue(provider != LLMProvider.OPEN_AI || (baseUrl != null && !baseUrl.trim().isEmpty()),
                "OpenAI提供商必须设置基础URL");
        Assert.isTrue(provider != LLMProvider.DASH_SCOPE || (baseUrl != null && !baseUrl.trim().isEmpty()),
                "DashScope提供商必须设置基础URL");
    }
}
