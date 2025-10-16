package io.github.anthem37.craft.infrastructure.memory.domain.factory.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import dev.langchain4j.community.model.dashscope.QwenTokenCountEstimator;
import dev.langchain4j.model.TokenCountEstimator;
import io.github.anthem37.craft.domain.memory.model.factory.ITokenCountEstimatorFactory;
import io.github.anthem37.craft.infrastructure.llm.mybatis.mapper.ILLMConfigMapper;
import io.github.anthem37.craft.infrastructure.llm.mybatis.po.LLMConfigPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hb28301
 * @date 2025/10/16 13:53:25
 */
@Component
@RequiredArgsConstructor
public class TokenCountEstimatorFactory implements ITokenCountEstimatorFactory {

    private final ILLMConfigMapper llmConfigMapper;

    @Override
    public TokenCountEstimator createTokenCountEstimator(Long llmConfigId) {
        LLMConfigPO llmConfigPO = llmConfigMapper.selectById(llmConfigId);
        Assert.isTrue(ObjectUtil.isNotEmpty(llmConfigPO), "LLM配置不存在，ID：{}", llmConfigId);
        String apiKey = llmConfigPO.getApiKey();
        String modelName = llmConfigPO.getModelName();

        return new QwenTokenCountEstimator(apiKey, modelName);
    }

}
