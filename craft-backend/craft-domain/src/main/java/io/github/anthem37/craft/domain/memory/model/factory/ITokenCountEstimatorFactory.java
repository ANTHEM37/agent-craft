package io.github.anthem37.craft.domain.memory.model.factory;

import dev.langchain4j.model.TokenCountEstimator;

/**
 * @author hb28301
 * @date 2025/10/16 13:50:42
 */
public interface ITokenCountEstimatorFactory {

    /**
     * 创建令牌计数估计器
     *
     * @param llmConfigId LLM配置ID
     * @return 令牌计数估计器
     */
    TokenCountEstimator createTokenCountEstimator(Long llmConfigId);
}
