package io.github.anthem37.craft.domain.memory.model.value;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageType;
import io.github.anthem37.easy.ddd.domain.model.IValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天内容
 *
 * @author hb28301
 * @since 2025/10/15 17:49:50
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatContent implements IValueObject {

    /**
     * 消息列表
     */
    private List<ChatMessageData> messages;

    /**
     * 转换为LangChain4j的ChatMessage列表
     *
     * @return LangChain4j的ChatMessage列表
     */

    public List<ChatMessage> toLangChain4jChatMessages() {
        if (CollectionUtil.isEmpty(messages)) {
            return new ArrayList<>();
        }
        return messages.stream().map(ChatMessageData::toLangChain4jChatMessage).toList();
    }

    @Slf4j
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class ChatMessageData {

        /**
         * 消息类型
         */
        private ChatMessageType type;

        /**
         * 消息内容
         */
        private String content;

        /**
         * 转换为LangChain4j的ChatMessage
         *
         * @return LangChain4j的ChatMessage
         */
        public ChatMessage toLangChain4jChatMessage() {

            return JSONUtil.toBean(content, type.messageClass());
        }
    }

    public static ChatContent fromLangChain4jChatMessages(List<ChatMessage> messages) {
        if (CollectionUtil.isEmpty(messages)) {
            return new ChatContent();
        }
        return new ChatContent(messages.stream().map(message -> new ChatMessageData(message.type(), JSONUtil.toJsonStr(message))).toList());
    }
}
