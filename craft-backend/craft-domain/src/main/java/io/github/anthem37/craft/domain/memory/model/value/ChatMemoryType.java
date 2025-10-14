package io.github.anthem37.craft.domain.memory.model.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 记忆类型枚举类
 *
 * @author hb28301
 * @date 2025/10/14 10:21:02
 */
@Getter
@AllArgsConstructor
public enum ChatMemoryType {

    /**
     * 消息窗口记忆类型
     */
    MESSAGE_WINDOW("memory::message_window"),

    /**
     * 令牌窗口记忆类型
     */
    TOKEN_WINDOW("memory::token_window"),

    /**
     * 摘要型记忆（Summary-based）
     * <p>
     * 思路：定期把过往对话自动压缩为摘要，仅保留关键信息，减少上下文长度。
     * 适用：长会话但又需要低成本保持“脉络感”。
     * 优点：上下文短、成本低；缺点：可能丢细节。
     */
    SUMMARY_BASED("memory::summary_based"),
    ;

    /**
     * 内存配置ID
     */
    private final String id;

}
