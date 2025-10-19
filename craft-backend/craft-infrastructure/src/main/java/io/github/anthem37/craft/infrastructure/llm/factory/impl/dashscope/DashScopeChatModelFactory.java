package io.github.anthem37.craft.infrastructure.llm.factory.impl.dashscope;

import cn.hutool.core.util.ObjectUtil;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatRequestParameters;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.domain.llm.model.value.ExtraInfo;
import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;
import io.github.anthem37.craft.domain.llm.repository.ILLMConfigDomainRepository;
import io.github.anthem37.craft.infrastructure.llm.factory.AbstractChatModelFactory;
import io.github.anthem37.craft.infrastructure.llm.factory.IChatModelInnerFactory;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/11 16:37:49
 */
@Component
public class DashScopeChatModelFactory extends AbstractChatModelFactory implements IChatModelInnerFactory {

    public DashScopeChatModelFactory(ILLMConfigDomainRepository llmConfigDomainRepository) {
        super(llmConfigDomainRepository);
    }

    @Override
    public ChatModel createChatModel(LLMConfig llmConfig) {
        QwenChatModel.QwenChatModelBuilder builder = QwenChatModel.builder()
                .baseUrl(llmConfig.getBaseUrl())
                .apiKey(llmConfig.getApiKey())
                .modelName(llmConfig.getModelName());

        ExtraInfo extra = llmConfig.getExtraInfo();
        if (extra != null) {
            builder = builder.defaultRequestParameters(OpenAiChatRequestParameters.builder()
                    .modelName(llmConfig.getModelName())
                    .temperature(extra.getTemperature())
                    .topP(extra.getTopP())
                    .topK(extra.getTopK())
                    .frequencyPenalty(extra.getFrequencyPenalty())
                    .presencePenalty(extra.getPresencePenalty())
                    .maxOutputTokens(extra.getMaxOutputTokens())
                    .stopSequences(extra.getStopSequences())
                    .toolSpecifications(resolveToolSpecifications(extra.getToolNames()))
                    .toolChoice(extra.getToolChoice())
                    .responseFormat(toResponseFormat(extra.getResponseFormatType()))
                    .build());
            builder.listeners(resolveListeners(extra.getChatModelListenerNames()));
        }

        return builder.build();
    }

    @Override
    public StreamingChatModel createStreamingChatModel(LLMConfig llmConfig) {
        QwenStreamingChatModel.QwenStreamingChatModelBuilder builder = QwenStreamingChatModel.builder()
                .baseUrl(llmConfig.getBaseUrl())
                .apiKey(llmConfig.getApiKey())
                .modelName(llmConfig.getModelName());

        ExtraInfo extra = llmConfig.getExtraInfo();
        if (extra != null) {
            builder = builder.defaultRequestParameters(OpenAiChatRequestParameters.builder()
                    .modelName(llmConfig.getModelName())
                    .temperature(extra.getTemperature())
                    .topP(extra.getTopP())
                    .topK(extra.getTopK())
                    .frequencyPenalty(extra.getFrequencyPenalty())
                    .presencePenalty(extra.getPresencePenalty())
                    .maxOutputTokens(extra.getMaxOutputTokens())
                    .stopSequences(extra.getStopSequences())
                    .toolSpecifications(resolveToolSpecifications(extra.getToolNames()))
                    .toolChoice(extra.getToolChoice())
                    .responseFormat(toResponseFormat(extra.getResponseFormatType()))
                    .build());
            builder.listeners(resolveListeners(extra.getChatModelListenerNames()));
        }

        return builder.build();
    }

    @Override
    public boolean supports(LLMConfig modelName) {

        return ObjectUtil.equals(modelName.getProvider(), LLMProvider.DASH_SCOPE);
    }
}
