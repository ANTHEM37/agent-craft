package io.github.anthem37.craft.domain.llm.model.value;

import io.github.anthem37.easy.ddd.domain.model.IValueObject;

/**
 * LLM提供商枚举
 *
 * @author hb28301
 * @since 2025/10/16 14:24:28
 */
public enum LLMProvider implements IValueObject {
    OPEN_AI(),
    DASH_SCOPE(),
    ;
}
