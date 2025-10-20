package io.github.anthem37.craft.infrastructure.memory.chatmemory.impl;

import cn.hutool.core.lang.Assert;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import io.github.anthem37.craft.infrastructure.memory.chatmemory.AbstractChatMemory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 摘要窗口聊天记忆
 * 当消息数量超过阈值时，会对历史消息进行摘要压缩，但保留最新的用户消息不被压缩
 *
 * @author hb28301
 * @since 2025/10/17 13:35:47
 */
@Slf4j
public class SummaryWindowChatMemory extends AbstractChatMemory {

    private final Object id;
    private final Integer maxMessages;
    private final ChatModel chatModel;
    private final ChatMemoryStore store;

    /**
     * 构造函数
     *
     * @param id          记忆ID
     * @param maxMessages 最大消息数阈值
     * @param chatModel   用于生成摘要的聊天模型
     * @param store       消息存储
     */
    private SummaryWindowChatMemory(Object id, Integer maxMessages, ChatModel chatModel, ChatMemoryStore store) {
        this.id = id;
        this.maxMessages = maxMessages;
        this.chatModel = chatModel;
        this.store = store;
    }

    /**
     * 创建构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Object id() {
        return id;
    }

    @Override
    public void add(ChatMessage message) {
        List<ChatMessage> messages = messages();

        // 对于摘要窗口，需要特殊处理系统消息
        addMessageForSummaryWindow(messages, message);

        // 如果消息数量超过阈值，进行摘要压缩
        if (messages.size() > maxMessages) {
            compressMessages(messages);
        } else {
            // 如果不需要压缩，直接更新存储
            store.updateMessages(id, messages);
        }
    }

    /**
     * 为摘要窗口添加消息的特殊逻辑
     * 允许同时存在原始系统消息和摘要系统消息
     *
     * @param messages 消息列表
     * @param message  要添加的消息
     */
    private void addMessageForSummaryWindow(List<ChatMessage> messages, ChatMessage message) {
        if (message instanceof SystemMessage) {
            SystemMessage newSystemMessage = (SystemMessage) message;
            String newText = newSystemMessage.text();

            // 如果是摘要消息，替换现有的摘要消息
            if (newText.startsWith("对话历史摘要：")) {
                // 移除现有的摘要消息
                messages.removeIf(msg -> msg instanceof SystemMessage && ((SystemMessage) msg).text().startsWith("对话历史摘要："));
            } else {
                // 如果是普通系统消息，替换现有的非摘要系统消息
                messages.removeIf(msg -> msg instanceof SystemMessage && !((SystemMessage) msg).text().startsWith("对话历史摘要："));
            }
        }
        messages.add(message);
    }

    @Override
    public List<ChatMessage> messages() {
        return store.getMessages(id);
    }

    @Override
    public void clear() {
        store.deleteMessages(id);
    }

    /**
     * 压缩消息：对历史消息进行摘要，但保留最新的用户消息
     *
     * @param messages 当前所有消息
     */
    private void compressMessages(List<ChatMessage> messages) {
        try {
            // 找到最后一条用户消息的位置
            int lastUserMessageIndex = -1;
            for (int i = messages.size() - 1; i >= 0; i--) {
                if (messages.get(i) instanceof UserMessage) {
                    lastUserMessageIndex = i;
                    break;
                }
            }

            // 如果没有用户消息或者消息数量不足以压缩，直接返回
            if (lastUserMessageIndex == -1 || lastUserMessageIndex < maxMessages / 2) {
                return;
            }

            // 分离原始系统消息、需要压缩的消息和需要保留的消息
            SystemMessage originalSystemMessage = null;
            List<ChatMessage> messagesToCompress = new ArrayList<>();
            List<ChatMessage> messagesToKeep = new ArrayList<>();

            for (int i = 0; i < messages.size(); i++) {
                ChatMessage message = messages.get(i);
                if (message instanceof SystemMessage) {
                    // 只保留第一个非摘要的系统消息作为原始系统消息
                    if (originalSystemMessage == null && !((SystemMessage) message).text().startsWith("对话历史摘要：")) {
                        originalSystemMessage = (SystemMessage) message;
                    }
                    // 摘要系统消息会被新摘要替换，原始系统消息保留
                } else if (i < lastUserMessageIndex) {
                    messagesToCompress.add(message);
                } else {
                    messagesToKeep.add(message);
                }
            }

            // 如果没有需要压缩的消息，直接返回
            if (messagesToCompress.isEmpty()) {
                return;
            }

            // 生成摘要
            String summary = generateSummary(messagesToCompress);

            // 创建独立的摘要系统消息
            SystemMessage summaryMessage = SystemMessage.from("对话历史摘要：" + summary);

            // 重新组织消息：原始系统消息（如果存在） + 摘要系统消息 + 保留的消息
            List<ChatMessage> newMessages = new ArrayList<>();
            if (originalSystemMessage != null) {
                newMessages.add(originalSystemMessage);
            }
            newMessages.add(summaryMessage);
            newMessages.addAll(messagesToKeep);

            // 更新存储
            store.updateMessages(id, newMessages);

            log.info("消息压缩完成，原消息数量: {}, 压缩后消息数量: {}", messages.size(), newMessages.size());

        } catch (Exception e) {
            log.error("消息压缩失败", e);
            // 压缩失败时，简单删除最早的消息以保持在阈值内
            fallbackCompression(messages);
        }
    }

    /**
     * 生成消息摘要
     *
     * @param messages 需要摘要的消息列表
     * @return 摘要内容
     */
    private String generateSummary(List<ChatMessage> messages) {
        if (messages.isEmpty()) {
            return "无对话内容";
        }

        // 构建摘要提示
        StringBuilder conversationBuilder = new StringBuilder();
        conversationBuilder.append("请对以下对话内容进行简洁的摘要，保留关键信息和上下文：\n\n");

        for (ChatMessage message : messages) {
            if (message instanceof UserMessage userMessage) {
                conversationBuilder.append("用户: ").append(userMessage.singleText()).append("\n");
            } else if (message instanceof AiMessage aiMessage) {
                conversationBuilder.append("助手: ").append(aiMessage.text()).append("\n");
            }
        }

        conversationBuilder.append("\n请用简洁的语言总结上述对话的主要内容和关键信息：");

        try {
            UserMessage summaryRequest = UserMessage.from(conversationBuilder.toString());
            AiMessage summaryResponse = chatModel.chat(summaryRequest).aiMessage();
            return summaryResponse.text();
        } catch (Exception e) {
            log.error("生成摘要失败", e);
            return "对话摘要生成失败，包含 " + messages.size() + " 条历史消息";
        }
    }

    /**
     * 备用压缩策略：简单删除最早的消息
     *
     * @param messages 当前消息列表
     */
    private void fallbackCompression(List<ChatMessage> messages) {
        try {
            // 保留系统消息和最新的消息
            List<ChatMessage> systemMessages = new ArrayList<>();
            List<ChatMessage> otherMessages = new ArrayList<>();

            for (ChatMessage message : messages) {
                if (message instanceof SystemMessage) {
                    systemMessages.add(message);
                } else {
                    otherMessages.add(message);
                }
            }

            // 只保留最新的 maxMessages - systemMessages.size() 条非系统消息
            int keepCount = Math.max(1, maxMessages - systemMessages.size());
            if (otherMessages.size() > keepCount) {
                otherMessages = otherMessages.subList(otherMessages.size() - keepCount, otherMessages.size());
            }

            List<ChatMessage> newMessages = new ArrayList<>();
            newMessages.addAll(systemMessages);
            newMessages.addAll(otherMessages);

            store.updateMessages(id, newMessages);

            log.info("备用压缩完成，原消息数量: {}, 压缩后消息数量: {}", messages.size(), newMessages.size());

        } catch (Exception e) {
            log.error("备用压缩也失败了", e);
        }
    }

    /**
     * 构建器
     */
    public static class Builder {
        private Object id;
        private Integer maxMessages;
        private ChatModel chatModel;
        private ChatMemoryStore store;

        public Builder id(Object id) {
            this.id = id;
            return this;
        }

        public Builder maxMessages(Integer maxMessages) {
            this.maxMessages = maxMessages;
            return this;
        }

        public Builder chatModel(ChatModel chatModel) {
            this.chatModel = chatModel;
            return this;
        }

        public Builder chatMemoryStore(ChatMemoryStore store) {
            this.store = store;
            return this;
        }

        public SummaryWindowChatMemory build() {
            Assert.notNull(id, "id 不能为空");
            Assert.isTrue(Optional.ofNullable(maxMessages).orElse(0) > 0, "maxMessages 必须大于 0");
            Assert.notNull(chatModel, "chatModel 不能为空");
            Assert.notNull(store, "store 不能为空");

            return new SummaryWindowChatMemory(id, maxMessages, chatModel, store);
        }
    }
}
