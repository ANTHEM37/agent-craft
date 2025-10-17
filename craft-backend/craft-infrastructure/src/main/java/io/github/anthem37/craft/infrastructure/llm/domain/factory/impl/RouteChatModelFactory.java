package io.github.anthem37.craft.infrastructure.llm.domain.factory.impl;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.domain.llm.model.factory.IChatModelFactory;
import io.github.anthem37.craft.domain.llm.repository.ILLMConfigDomainRepository;
import io.github.anthem37.craft.infrastructure.llm.domain.factory.AbstractChatModelFactory;
import io.github.anthem37.craft.infrastructure.llm.domain.factory.IChatModelInnerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 路由LLM模型工厂
 *
 * @author hb28301
 * @since 2025/10/17 10:53:50
 */
@Primary
@Component
public class RouteChatModelFactory extends AbstractChatModelFactory implements IChatModelFactory {

    private final List<IChatModelInnerFactory> chatModelInnerFactories;

    public RouteChatModelFactory(ILLMConfigDomainRepository llmConfigDomainRepository,
                                 List<IChatModelInnerFactory> chatModelInnerFactories) {
        super(llmConfigDomainRepository);
        this.chatModelInnerFactories = chatModelInnerFactories;
    }

    @Override
    public ChatModel createChatModel(LLMConfig llmConfig) {

        return chatModelInnerFactories.stream()
                .filter(factory -> factory.supports(llmConfig))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未知LLM供应商: " + llmConfig.getProvider()))
                .createChatModel(llmConfig);
    }

    @Override
    public StreamingChatModel createStreamingChatModel(LLMConfig llmConfig) {

        return chatModelInnerFactories.stream()
                .filter(factory -> factory.supports(llmConfig))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未知LLM供应商: " + llmConfig.getProvider()))
                .createStreamingChatModel(llmConfig);
    }
}
