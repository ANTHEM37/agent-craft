package io.github.anthem37.craft.domain.memory.model.entity;

import io.github.anthem37.craft.domain.common.model.entity.BaseEntity;
import io.github.anthem37.craft.domain.memory.model.value.ChatContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hb28301
 * @date 2025/10/15 17:48:36
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatMemory extends BaseEntity {

    /**
     * 消息内容
     */
    private ChatContent content;
}
