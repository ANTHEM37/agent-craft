package io.github.anthem37.craft.domain.memory.model.value;

import io.github.anthem37.easy.ddd.domain.model.IValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 记忆类型枚举类
 *
 * @author hb28301
 * @since 2025/10/14 10:21:02
 */
@Getter
@AllArgsConstructor
public enum ChatMemoryType implements IValueObject {

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
