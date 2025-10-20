package io.github.anthem37.craft.adaptor.web.memory.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 删除记忆配置请求（Web DTO）
 *
 * @author hb28301
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DeleteChatMemoryConfigRequest {

    @NotNull(message = "ID不能为空")
    private Long id;
}
