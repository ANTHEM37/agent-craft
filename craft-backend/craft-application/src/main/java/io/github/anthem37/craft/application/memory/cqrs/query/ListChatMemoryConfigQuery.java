package io.github.anthem37.craft.application.memory.cqrs.query;

import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 查询聊天记忆配置列表查询
 *
 * @author hb28301
 * @since 2025/10/11 17:10:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ListChatMemoryConfigQuery implements IQuery<List<ChatMemoryConfigDTO>> {

    /**
     * 配置名称，用于标识不同的记忆配置
     */
    private String configName;

    /**
     * 记忆类型
     */
    private ChatMemoryType chatMemoryType;
}
