package io.github.anthem37.craft.application.memory.dto.command;

import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommand;

/**
 * 创建聊天记忆配置命令
 *
 * @author hb28301
 * @since 2025/10/11 17:07:21
 */
public class UpdateChatMemoryConfigCommand extends ChatMemoryConfigDTO implements ICommand<Boolean> {
    @Override
    public boolean isValid() {
        Assert.notNull(getId(), "ID不能为空");
        Assert.hasText(getConfigName(), "配置名称不能为空");
        Assert.notNull(getChatMemoryType(), "记忆类型不能为空");
        Assert.notNull(getParams(), "记忆参数不能为空");
        Assert.hasText(getDescription(), "记忆描述不能为空");
        return true;
    }
}
