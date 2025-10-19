package io.github.anthem37.craft.infrastructure.llm.factory;

import cn.hutool.core.lang.Assert;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.craft.domain.llm.model.factory.IChatModelFactory;
import io.github.anthem37.craft.domain.llm.repository.ILLMConfigDomainRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * @author hb28301
 * @date 2025/10/17 14:28:21
 */
@RequiredArgsConstructor
public abstract class AbstractChatModelFactory implements IChatModelFactory {

    private final ILLMConfigDomainRepository llmConfigDomainRepository;

    @Override
    public ChatModel createChatModel(Long llmConfigId) {

        return createChatModel(getLlmConfig(llmConfigId));
    }

    @Override
    public StreamingChatModel createStreamingChatModel(Long llmConfigId) {


        return createStreamingChatModel(getLlmConfig(llmConfigId));
    }

    /**
     * 获取LLM配置
     *
     * @param llmConfigId LLM配置ID
     * @return LLM配置
     */
    private LLMConfig getLlmConfig(Long llmConfigId) {
        Optional<LLMConfig> llmConfigOptional = llmConfigDomainRepository.findById(llmConfigId);
        Assert.isTrue(llmConfigOptional.isPresent(), "LLM配置不存在，ID：{}", llmConfigId);

        return llmConfigOptional.get();
    }

}
