package io.github.anthem37.craft.application.llm.factory;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;

/**
 * @author hb28301
 * @since 2025/10/11 16:36:04
 */
public interface IChatModelFactory {

    /**
     * 创建一个新的聊天模型
     *
     * @param llmConfig 模型名称
     * @return 聊天模型
     */
    ChatModel createChatModel(LLMConfig llmConfig);

    /**
     * 创建一个新的流式聊天模型
     *
     * @param llmConfig 模型名称
     * @return 流式聊天模型
     */
    StreamingChatModel createStreamingChatModel(LLMConfig llmConfig);
}
