package io.github.anthem37.craft.infrastructure.llm.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.llm.assembler.LLMConfigDTOAssembler;
import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.application.llm.repository.ILLMConfigQueryRepository;
import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;
import io.github.anthem37.craft.infrastructure.common.po.BasePO;
import io.github.anthem37.craft.infrastructure.llm.assembler.LLMConfigPersistenceAssembler;
import io.github.anthem37.craft.infrastructure.llm.mybatis.mapper.ILLMConfigMapper;
import io.github.anthem37.craft.infrastructure.llm.mybatis.po.LLMConfigPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * LLM配置仓库实现类
 *
 * @author hb28301
 * @since 2025/10/11 15:39:31
 */
@Repository
@RequiredArgsConstructor
public class LLMConfigQueryRepository implements ILLMConfigQueryRepository {

    private final ILLMConfigMapper llmConfigMapper;

    @Override
    public LLMConfigDTO findById(Long id) {
        LLMConfigPO po = new LambdaQueryChainWrapper<>(llmConfigMapper).eq(LLMConfigPO::getId, id).one();

        return LLMConfigDTOAssembler.INSTANCE.toDTO(LLMConfigPersistenceAssembler.INSTANCE.toDomain(po));
    }

    @Override
    public List<LLMConfigDTO> listByModelNameAndConfigNameAndProvider(String modelName, String configName, LLMProvider provider) {

        return new LambdaQueryChainWrapper<>(llmConfigMapper)
                .like(StrUtil.isNotBlank(modelName), LLMConfigPO::getModelName, modelName)
                .like(StrUtil.isNotBlank(configName), LLMConfigPO::getConfigName, configName)
                .eq(provider != null, LLMConfigPO::getProvider, provider)
                .orderByDesc(BasePO::getUpdatedAt)
                .list()
                .stream()
                .map(po -> LLMConfigDTOAssembler.INSTANCE.toDTO(LLMConfigPersistenceAssembler.INSTANCE.toDomain(po)))
                .toList();
    }

    @Override
    public Long countByModelNameAndConfigNameAndProvider(String modelName, String configName, LLMProvider provider) {

        return new LambdaQueryChainWrapper<>(llmConfigMapper)
                .like(StrUtil.isNotBlank(modelName), LLMConfigPO::getModelName, modelName)
                .like(StrUtil.isNotBlank(configName), LLMConfigPO::getConfigName, configName)
                .eq(provider != null, LLMConfigPO::getProvider, provider)
                .count();
    }

    @Override
    public PageDTO<LLMConfigDTO> pageByModelNameAndConfigNameAndProvider(long current, long size, String modelName, String configName, LLMProvider provider) {
        Page<LLMConfigPO> page = new LambdaQueryChainWrapper<>(llmConfigMapper)
                .like(StrUtil.isNotBlank(modelName), LLMConfigPO::getModelName, modelName)
                .like(StrUtil.isNotBlank(configName), LLMConfigPO::getConfigName, configName)
                .eq(provider != null, LLMConfigPO::getProvider, provider)
                .orderByDesc(BasePO::getUpdatedAt)
                .page(new Page<>(current, size));

        return PageDTO.of(page.getCurrent(), page.getSize(), page.getTotal(),
                page.getRecords().stream()
                        .map(po -> LLMConfigDTOAssembler.INSTANCE.toDTO(LLMConfigPersistenceAssembler.INSTANCE.toDomain(po)))
                        .toList());
    }

}
