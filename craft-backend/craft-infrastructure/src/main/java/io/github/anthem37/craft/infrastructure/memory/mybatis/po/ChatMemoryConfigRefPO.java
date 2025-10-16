package io.github.anthem37.craft.infrastructure.memory.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.anthem37.craft.infrastructure.common.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天内存配置实体类
 *
 * @author hb28301
 * @since 2025/10/14 10:20:06
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_chat_memory_config_ref")
public class ChatMemoryConfigRefPO extends BasePO {

    /**
     * 配置名称，用于标识不同的记忆配置
     */
    @TableField(value = "config_name")
    private Long configId;

    /**
     * 记忆ID
     */
    @TableField(value = "memory_id")
    private Long memoryId;

}
