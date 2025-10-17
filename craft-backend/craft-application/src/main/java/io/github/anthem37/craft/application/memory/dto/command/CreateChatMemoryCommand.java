package io.github.anthem37.craft.application.memory.dto.command;

import dev.langchain4j.memory.ChatMemory;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 创建聊天记忆命令
 * 用于创建ChatMemory实例的命令对象
 *
 * @author hb28301
 * @since 2025/01/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreateChatMemoryCommand implements ICommand<ChatMemory> {

    /**
     * 聊天记忆配置ID
     * 用于指定要使用的记忆配置
     */
    @NotNull
    private Long configId;

    @Override
    public boolean isValid() {
        Assert.notNull(configId, "配置ID不能为空");
        return true;
    }
}