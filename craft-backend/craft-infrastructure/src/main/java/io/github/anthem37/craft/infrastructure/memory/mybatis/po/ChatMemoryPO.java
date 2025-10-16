package io.github.anthem37.craft.infrastructure.memory.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.anthem37.craft.domain.memory.model.value.ChatContent;
import io.github.anthem37.craft.infrastructure.common.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author hb28301
 * @since 2025/10/14 14:49:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_chat_memory")
public class ChatMemoryPO extends BasePO {

    /**
     * 消息内容
     */
    @TableField(value = "content", typeHandler = JacksonTypeHandler.class)
    private ChatContent content;

}
