package io.github.anthem37.craft.application.memory.dto.query;

import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.easy.ddd.common.assertion.Assert;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQuery;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询单个聊天记忆配置查询
 *
 * @author hb28301
 * @since 2025/10/11 17:10:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FindOneChatMemoryConfigQuery implements IQuery<ChatMemoryConfigDTO> {

    /**
     * 要查询的LLM配置ID
     */
    @NotNull(message = "记忆配置ID不能为空")
    private Long id;

    @Override
    public boolean isValid() {
        Assert.notNull(id, "记忆配置ID不能为空");
        return true;
    }
}
