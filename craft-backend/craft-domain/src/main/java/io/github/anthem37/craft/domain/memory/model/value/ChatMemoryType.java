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
    MESSAGE_WINDOW(),

    /**
     * 令牌窗口记忆类型
     */
    TOKEN_WINDOW(),
    ;

}
