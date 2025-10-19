package io.github.anthem37.craft.adaptor.web.memory.response;

import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryConfigParams;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatMemoryConfigResponse {
    private Long id;
    private String configName;
    private ChatMemoryType chatMemoryType;
    private ChatMemoryConfigParams params;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private String createdByUsername;
    private Long updatedBy;
    private String updatedByUsername;
    private Long deleted;
}
