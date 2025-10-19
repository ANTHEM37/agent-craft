package io.github.anthem37.craft.infrastructure.common.config.redis;

/**
 * Redis 键名
 *
 * @author hb28301
 * @date 2025/10/17 11:38:55
 */
public class RedisKeys {

    /**
     * 聊天记忆键名
     */
    public static class ChatMemory {
        public static final String PREFIX = "chat:memory:";

        /**
         * 聊天内存内容键
         *
         * @param memoryId 内存ID
         * @return 聊天内存内容键
         */
        public static String contentKey(Object memoryId) {
            return PREFIX + "content:" + memoryId;
        }
    }
}
