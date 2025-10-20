package io.github.anthem37.craft.application.memory.cqrs.command;

import io.github.anthem37.easy.ddd.common.assertion.Assert;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 删除聊天记忆配置命令
 *
 * @author hb28301
 * @since 2025/10/11 17:07:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DeleteChatMemoryConfigCommand implements ICommand<Boolean> {

    /**
     * 要删除的LLM配置ID
     */
    @NotNull
    private Long id;

    @Override
    public boolean isValid() {
        Assert.notNull(id, "ID不能为空");
        return true;
    }
}
