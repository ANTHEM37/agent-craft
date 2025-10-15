package io.github.anthem37.craft.infrastructure.memory.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import dev.langchain4j.data.message.ChatMessageType;
import io.github.anthem37.craft.infrastructure.common.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.EnumTypeHandler;

/**
 * @author hb28301
 * @date 2025/10/14 14:49:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_chat_message")
public class ChatMessagePO extends BasePO {

    /**
     * 记忆ID
     */
    @TableField(value = "memory_id")
    private Long memoryId;

    /**
     * 消息类型
     */
    @TableField(value = "type", typeHandler = EnumTypeHandler.class)
    private ChatMessageType type;

    /**
     * 消息内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 发送人id
     */
    @TableField(value = "sender_id")
    private Long senderId;

}
