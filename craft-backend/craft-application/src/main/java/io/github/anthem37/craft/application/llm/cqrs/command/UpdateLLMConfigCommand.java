package io.github.anthem37.craft.application.llm.cqrs.command;

import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommand;

/**
 * 更新LLM配置命令
 *
 * @author hb28301
 * @since 2025/10/11 17:07:21
 */
public class UpdateLLMConfigCommand extends LLMConfigDTO implements ICommand<Boolean> {
    @Override
    public boolean isValid() {
        Assert.notNull(getId(), "ID不能为空");
        Assert.hasText(getBaseUrl(), "API基础URL不能为空");
        Assert.hasText(getApiKey(), "API密钥不能为空");
        Assert.hasText(getModelName(), "模型名称不能为空");
        return true;
    }
}
