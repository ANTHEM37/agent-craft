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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatMemoryConfigParams {

    /**
     * 聊天记忆存储类型
     */
    private ChatMemoryStoreType chatMemoryStoreType;

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
         * LLM配置ID
         */
        private Long llmConfigId;
    }

}
