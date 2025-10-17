package io.github.anthem37.craft.infrastructure.memory.domain.memory;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.memory.ChatMemory;

import java.util.List;
import java.util.Optional;

/**
 * @author hb28301
 * @since 2025/10/14 13:57:45
 */
public abstract class AbstractChatMemory implements ChatMemory {

    /**
     * 添加消息到消息列表
     *
     * @param messages 消息列表
     * @param message  消息
     */
    protected void addMessage(List<ChatMessage> messages, ChatMessage message) {
        // 系统消息只能存在一个, 如果存在, 则替换, 系统消息的作用是设置上下文环境, 比如设置角色、指令等
        if (message instanceof SystemMessage) {
            Optional<SystemMessage> systemMessage = SystemMessage.findFirst(messages);
            if (systemMessage.isPresent()) {
                if (systemMessage.get().equals(message)) {
                    return;
                } else {
                    messages.remove(systemMessage.get());
                }
            }
        }
        messages.add(message);
    }

}
