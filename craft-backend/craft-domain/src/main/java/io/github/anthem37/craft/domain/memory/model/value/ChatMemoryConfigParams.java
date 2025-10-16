package io.github.anthem37.craft.domain.memory.model.value;

import io.github.anthem37.easy.ddd.domain.model.IValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 聊天记忆配置参数
 *
 * @author hb28301
 * @since 2025/10/14 10:45:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatMemoryConfigParams implements IValueObject {

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
         * LLM配置ID
         */
        private Long llmConfigId;

        /**
         * 最大令牌数
         */
        private Integer maxTokens;
    }

}
