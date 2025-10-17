package io.github.anthem37.craft.infrastructure.memory.domain.store.impl;

import cn.hutool.core.collection.CollectionUtil;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import io.github.anthem37.craft.domain.memory.model.value.ChatContent;
import io.github.anthem37.craft.infrastructure.common.config.RedisChatMemoryStoreConfig;
import io.github.anthem37.craft.infrastructure.common.config.RedisKeys;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Redis 存储聊天记忆（推荐使用）
 *
 * @author hb28301
 * @since 2025/10/17 11:07:25
 */
@Component
@RequiredArgsConstructor
public class RedisChatMemoryStore implements ChatMemoryStore {

    private final RedisChatMemoryStoreConfig config;
    private final RedissonClient redissonClient;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        RBucket<ChatContent> bucket = redissonClient.getBucket(contentKey(memoryId));
        List<ChatMessage> langChain4jChatMessages = new ArrayList<>();
        if (bucket.isExists()) {
            ChatContent content = bucket.get();
            langChain4jChatMessages = content.toLangChain4jChatMessages();
            //延长过期时间
            bucket.expire(Instant.now().plusSeconds(config.getRenewExpire()));
        }

        return langChain4jChatMessages;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        if (CollectionUtil.isEmpty(messages)) {
            return;
        }

        ChatContent content = ChatContent.fromLangChain4jChatMessages(messages);
        RBucket<ChatContent> bucket = redissonClient.getBucket(contentKey(memoryId));
        bucket.set(content);
        bucket.expire(Instant.now().plusSeconds(config.getInitialExpire()));
    }

    @Override
    public void deleteMessages(Object memoryId) {
        redissonClient.getBucket(contentKey(memoryId)).delete();
    }

    /**
     * 聊天内存内容键
     *
     * @param memoryId 记忆ID
     * @return 聊天内存内容键
     */
    private String contentKey(Object memoryId) {

        return RedisKeys.ChatMemory.contentKey(memoryId);
    }
}
