package io.github.anthem37.craft.domain.memory.model.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 聊天记忆配置参数
 *
 * @author hb28301
 * @date 2025/10/14 10:45:26
 */
public class ChatMemoryConfigParams {

    /**
     * 消息窗口参数
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class MessageWindowParams extends ChatMemoryConfigParams {

        /**
         * 最大消息数
         */
        private Integer maxMessages;

        /**
         * 聊天记忆存储类型
         */
        private ChatMemoryStoreType chatMemoryStoreType;
    }

    /**
     * 令牌窗口参数
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class TokenWindowParams extends ChatMemoryConfigParams {

        /**
         * 最大令牌数
         */
        private Integer maxTokens;

        /**
         * 令牌计数估计器类型
         */
        private TokenCountEstimatorType tokenCountEstimatorType;

        /**
         * 聊天记忆存储类型
         */
        private ChatMemoryStoreType chatMemoryStoreType;
    }

    /**
     * 摘要型记忆参数
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class SummaryBasedParams extends ChatMemoryConfigParams {

        /**
         * 摘要最大令牌数（可选，未提供时走默认）
         */
        private Integer summaryMaxTokens;

        /**
         * 每多少条消息触发一次摘要（可选，未提供时走默认）
         */
        private Integer summarizeEveryMessages;

        /**
         * 令牌计数估计器类型
         */
        private TokenCountEstimatorType tokenCountEstimatorType;

        /**
         * 聊天记忆存储类型
         */
        private ChatMemoryStoreType chatMemoryStoreType;
    }

}
