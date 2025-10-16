package io.github.anthem37.craft.application.memory.dto.query;

import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQuery;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 列表查询LLM配置
 *
 * @author hb28301
 * @since 2025/10/11 17:10:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageChatMemoryConfigQuery implements IQuery<PageDTO<ChatMemoryConfigDTO>> {

    /**
     * 当前页码
     */
    @Min(value = 1, message = "当前页码必须大于等于1")
    @Max(value = 100, message = "当前页码必须小于等于100")
    private long current = 1;
    /**
     * 每页数量
     */
    @Min(value = 1, message = "每页数量必须大于等于1")
    @Max(value = 100, message = "每页数量必须小于等于100")
    private long size = 10;

    /**
     * 配置名称，用于标识不同的记忆配置
     */
    private String configName;

    /**
     * 记忆类型
     */
    private ChatMemoryType chatMemoryType;
}
