package io.github.anthem37.craft.infrastructure.llm.domain.factory;

import cn.hutool.extra.spring.SpringUtil;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.ResponseFormatType;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.domain.llm.model.factory.IChatModelFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author hb28301
 * @date 2025/10/16 17:43:09
 */
public interface IChatModelInnerFactory extends IChatModelFactory {

    boolean supports(LLMConfig modelName);


    default List<ToolSpecification> resolveToolSpecifications(List<String> toolNames) {
        return Optional.ofNullable(toolNames)
                .map(names -> names.stream()
                        .map(name -> SpringUtil.getBean(name, ToolSpecification.class))
                        .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);
    }

    default ResponseFormat toResponseFormat(ResponseFormatType type) {
        if (type == null) {
            return null;
        }
        return switch (type) {
            case TEXT -> ResponseFormat.TEXT;
            case JSON -> ResponseFormat.JSON;
        };
    }

    default List<ChatModelListener> resolveListeners(List<String> listenerNames) {
        return Optional.ofNullable(listenerNames)
                .map(names -> names.stream()
                        .map(name -> SpringUtil.getBean(name, ChatModelListener.class))
                        .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);
    }
}
