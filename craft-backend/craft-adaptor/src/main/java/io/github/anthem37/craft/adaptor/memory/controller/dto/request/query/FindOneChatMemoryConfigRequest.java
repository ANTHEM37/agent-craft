package io.github.anthem37.craft.adaptor.web.memory.request.query;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询单个记忆配置请求（Web DTO）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FindOneChatMemoryConfigRequest {

    @NotNull(message = "记忆配置ID不能为空")
    private Long id;
}
