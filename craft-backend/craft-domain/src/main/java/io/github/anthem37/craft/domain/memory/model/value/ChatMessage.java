package io.github.anthem37.craft.domain.memory.model.value;

import dev.langchain4j.data.message.ChatMessageType;

/**
 * @author hb28301
 * @date 2025/10/15 17:49:50
 */
public class ChatMessage {

    /**
     * 消息类型
     */
    private ChatMessageType type;

    /**
     * 消息状态
     */
    private ChatMessageStatus status;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送人id
     */
    private Long senderId;
}
