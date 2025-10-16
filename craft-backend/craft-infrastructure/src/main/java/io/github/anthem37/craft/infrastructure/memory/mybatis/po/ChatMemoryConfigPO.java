package io.github.anthem37.craft.infrastructure.memory.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryConfigParams;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import io.github.anthem37.craft.infrastructure.common.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.EnumTypeHandler;

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
@TableName("tb_chat_memory_config")
public class ChatMemoryConfigPO extends BasePO {

    /**
     * 配置名称，用于标识不同的记忆配置
     */
    @TableField(value = "config_name")
    private String configName;

    /**
     * 记忆类型
     */
    @TableField(value = "chat_memory_type", typeHandler = EnumTypeHandler.class)
    private ChatMemoryType chatMemoryType;

    /**
     * 记忆参数
     */
    @TableField(value = "params", typeHandler = JacksonTypeHandler.class)
    private ChatMemoryConfigParams params;

    /**
     * 记忆描述
     */
    @TableField(value = "description")
    private String description;

}
