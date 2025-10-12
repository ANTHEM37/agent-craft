package io.github.anthem37.craft.infrastructure.common.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 基础模型类，包含通用的数据库字段
 *
 * @author hb28301
 * @since 2025/10/9 16:43:54
 */
@Data
@Accessors(chain = true)
public abstract class BasePO {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 创建人
     */
    @TableField("created_by")
    private Long createdBy;

    /**
     * 创建人用户名
     */
    @TableField("created_by_username")
    private String createdByUsername;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private Long updatedBy;

    /**
     * 更新人用户名
     */
    @TableField("updated_by_username")
    private String updatedByUsername;

    /**
     * 逻辑删除标记（0 未删除，1 删除）
     */
    @TableLogic(value = "0", delval = "id")
    private Long deleted;
}
