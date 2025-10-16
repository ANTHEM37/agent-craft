package io.github.anthem37.craft.application.memory.dto;

import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryConfigParams;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 聊天内存配置实体类
 *
 * @author hb28301
 * @since 2025/10/14 10:20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatMemoryConfigDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 配置名称，用于标识不同的记忆配置
     */
    @NotBlank(message = "配置名称不能为空")
    private String configName;

    /**
     * 记忆类型
     */
    @NotNull(message = "记忆类型不能为空")
    private ChatMemoryType chatMemoryType;

    /**
     * 记忆参数
     */
    @NotNull(message = "记忆参数不能为空")
    private ChatMemoryConfigParams params;

    /**
     * 记忆描述
     */
    @NotBlank(message = "记忆描述不能为空")
    private String description;

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
