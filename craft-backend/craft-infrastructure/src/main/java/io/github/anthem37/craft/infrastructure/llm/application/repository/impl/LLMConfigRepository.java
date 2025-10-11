package io.github.anthem37.craft.infrastructure.llm.application.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.application.llm.repository.ILLMConfigRepository;
import io.github.anthem37.craft.infrastructure.common.po.BasePO;
import io.github.anthem37.craft.infrastructure.llm.converter.ILLMConfigDOConverter;
import io.github.anthem37.craft.infrastructure.llm.mybatis.mapper.ILLMConfigMapper;
import io.github.anthem37.craft.infrastructure.llm.mybatis.po.LLMConfigPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * LLM配置仓库实现类
 *
 * @author hb28301
 * @date 2025/10/11 15:39:31
 */
@Component
@RequiredArgsConstructor
public class LLMConfigRepository implements ILLMConfigRepository {

    private final ILLMConfigMapper llmConfigMapper;

    @Override
    public LLMConfigDTO findById(Long id) {
        LLMConfigPO po = new LambdaQueryChainWrapper<>(llmConfigMapper).eq(LLMConfigPO::getId, id).one();

        return ILLMConfigDOConverter.INSTANCE.toDTO(po);
    }

    @Override
    public List<LLMConfigDTO> listByModelName(String modelName) {

        return new LambdaQueryChainWrapper<>(llmConfigMapper).like(StrUtil.isNotBlank(modelName), LLMConfigPO::getModelName, modelName).orderByDesc(BasePO::getUpdatedAt).list().stream().map(ILLMConfigDOConverter.INSTANCE::toDTO).toList();
    }

    @Override
    public Long countByModelName(String modelName) {

        return new LambdaQueryChainWrapper<>(llmConfigMapper).like(StrUtil.isNotBlank(modelName), LLMConfigPO::getModelName, modelName).count();
    }

}
