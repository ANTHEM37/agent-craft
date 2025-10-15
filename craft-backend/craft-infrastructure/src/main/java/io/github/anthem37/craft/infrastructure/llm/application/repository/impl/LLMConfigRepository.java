package io.github.anthem37.craft.infrastructure.llm.application.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.anthem37.craft.application.common.dto.PageDTO;
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
 * @since 2025/10/11 15:39:31
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
    public List<LLMConfigDTO> listByModelNameAndConfigName(String modelName, String configName) {

        return new LambdaQueryChainWrapper<>(llmConfigMapper)
                .like(StrUtil.isNotBlank(modelName), LLMConfigPO::getModelName, modelName)
                .like(StrUtil.isNotBlank(configName), LLMConfigPO::getLlmConfigName, configName)
                .orderByDesc(BasePO::getUpdatedAt)
                .list()
                .stream()
                .map(ILLMConfigDOConverter.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public Long countByModelName(String modelName) {

        return new LambdaQueryChainWrapper<>(llmConfigMapper)
                .like(StrUtil.isNotBlank(modelName), LLMConfigPO::getModelName, modelName)
                .count();
    }

    @Override
    public PageDTO<LLMConfigDTO> pageByModelNameAndConfigName(long current, long size, String modelName, String configName) {
        Page<LLMConfigPO> page = new LambdaQueryChainWrapper<>(llmConfigMapper)
                .like(StrUtil.isNotBlank(modelName), LLMConfigPO::getModelName, modelName)
                .like(StrUtil.isNotBlank(configName), LLMConfigPO::getLlmConfigName, configName)
                .orderByDesc(BasePO::getUpdatedAt)
                .page(new Page<>(current, size));

        return PageDTO.of(page.getCurrent(), page.getSize(), page.getTotal(),
                page.getRecords().stream().map(ILLMConfigDOConverter.INSTANCE::toDTO).toList());
    }

}
