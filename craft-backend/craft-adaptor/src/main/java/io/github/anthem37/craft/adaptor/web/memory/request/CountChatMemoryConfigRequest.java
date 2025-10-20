package io.github.anthem37.craft.adaptor.web.memory.request;

import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 统计记忆配置请求（Web DTO）
 *
 * @author hb28301
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CountChatMemoryConfigRequest {

    private String configName;

    private ChatMemoryType chatMemoryType;
}
