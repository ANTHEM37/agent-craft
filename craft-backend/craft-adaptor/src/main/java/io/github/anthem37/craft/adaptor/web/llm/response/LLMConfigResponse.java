package io.github.anthem37.craft.adaptor.web.llm.response;

import io.github.anthem37.craft.domain.llm.model.value.ExtraInfo;
import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LLMConfigResponse {
    private Long id;
    private String configName;
    private LLMProvider provider;
    private String baseUrl;
    private String apiKey;
    private String modelName;
    private ExtraInfo extraInfo;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private String createdByUsername;
    private Long updatedBy;
    private String updatedByUsername;
    private Long deleted;
}
