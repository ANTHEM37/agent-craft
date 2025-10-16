package io.github.anthem37.craft.infrastructure.llm.domain.factory.impl;

import cn.hutool.extra.spring.SpringUtil;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.ResponseFormatType;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.domain.llm.model.factory.IChatModelFactory;
import io.github.anthem37.craft.domain.llm.model.value.ExtraInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author hb28301
 * @since 2025/10/11 16:37:49
 */
@Slf4j
@Primary
@Component
public class ChatModelFactory implements IChatModelFactory {

    @Override
    public ChatModel createChatModel(LLMConfig llmConfig) {
        OpenAiChatModel.OpenAiChatModelBuilder builder = OpenAiChatModel.builder()
                .baseUrl(llmConfig.getBaseUrl())
                .apiKey(llmConfig.getApiKey())
                .modelName(llmConfig.getModelName());

        ExtraInfo extra = llmConfig.getExtraInfo();
        if (extra != null) {
            builder = builder
                    .temperature(extra.getTemperature())
                    .topP(extra.getTopP())
                    .maxTokens(extra.getMaxTokens())
                    .presencePenalty(extra.getPresencePenalty())
                    .frequencyPenalty(extra.getFrequencyPenalty())
                    .stop(extra.getStop())
                    .responseFormat(toResponseFormat(extra.getResponseFormatType()))
                    .returnThinking(extra.isReturnThinking())
                    .timeout(Duration.ofSeconds(extra.getTimeout()))
                    .maxRetries(extra.getMaxRetries())
                    .logger(log)
                    .logRequests(extra.isLogRequests())
                    .logResponses(extra.isLogResponses())
                    .listeners(resolveListeners(extra.getChatModelListenerNames()));
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
            builder = builder
                    .temperature(extra.getTemperature())
                    .topP(extra.getTopP())
                    .maxTokens(extra.getMaxTokens())
                    .presencePenalty(extra.getPresencePenalty())
                    .frequencyPenalty(extra.getFrequencyPenalty())
                    .stop(extra.getStop())
                    .responseFormat(toResponseFormat(extra.getResponseFormatType()))
                    .returnThinking(extra.isReturnThinking())
                    .timeout(Duration.ofSeconds(extra.getTimeout()))
                    .logger(log)
                    .logRequests(extra.isLogRequests())
                    .logResponses(extra.isLogResponses())
                    .listeners(resolveListeners(extra.getChatModelListenerNames()));
        }

        return builder.build();
    }

    private ResponseFormat toResponseFormat(ResponseFormatType type) {
        if (type == null) {
            return null;
        }
        return switch (type) {
            case TEXT -> ResponseFormat.TEXT;
            case JSON -> ResponseFormat.JSON;
        };
    }

    private List<ChatModelListener> resolveListeners(List<String> listenerNames) {
        return Optional.ofNullable(listenerNames)
                .map(names -> names.stream()
                        .map(name -> SpringUtil.getBean(name, ChatModelListener.class))
                        .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);
    }
}
