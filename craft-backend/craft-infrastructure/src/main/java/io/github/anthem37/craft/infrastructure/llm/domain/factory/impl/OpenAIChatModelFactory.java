package io.github.anthem37.craft.infrastructure.llm.domain.factory.impl;

import cn.hutool.core.util.ObjectUtil;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatRequestParameters;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.domain.llm.model.value.ExtraInfo;
import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;
import io.github.anthem37.craft.infrastructure.llm.domain.factory.IChatModelInnerFactory;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @since 2025/10/11 16:37:49
 */
@Component
public class OpenAIChatModelFactory implements IChatModelInnerFactory {

    @Override
    public ChatModel createChatModel(LLMConfig llmConfig) {
        OpenAiChatModel.OpenAiChatModelBuilder builder = OpenAiChatModel.builder()
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
        }

        return builder.build();
    }

    @Override
    public StreamingChatModel createStreamingChatModel(LLMConfig llmConfig) {
        OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder = OpenAiStreamingChatModel.builder()
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
        }

        return builder.build();
    }

    @Override
    public boolean supports(LLMConfig modelName) {

        return ObjectUtil.equals(modelName.getProvider(), LLMProvider.OPEN_AI);
    }
}
