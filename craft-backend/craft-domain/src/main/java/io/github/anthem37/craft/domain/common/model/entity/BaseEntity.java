package io.github.anthem37.craft.domain.common.model.entity;

import io.github.anthem37.easy.ddd.domain.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 基础模型类，包含通用的数据库字段
 *
 * @author hb28301
 * @date 2025/10/11 15:03:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BaseEntity extends AbstractEntity<Long> {

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建人用户名
     */
    private String createdByUsername;

    /**
     * 更新人
     */
    private Long updatedBy;

    /**
     * 更新人用户名
     */
    private String updatedByUsername;

    /**
     * 逻辑删除标记（0 未删除，1 删除）
     */
    private Long deleted;
}
