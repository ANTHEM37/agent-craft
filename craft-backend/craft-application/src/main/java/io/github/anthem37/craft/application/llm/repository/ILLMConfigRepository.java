package io.github.anthem37.craft.application.llm.repository;


import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.domain.llm.model.value.LLMProvider;

import java.util.List;

/**
 * LLM配置仓库接口
 *
 * @author hb28301
 * @since 2025/10/11 15:34:10
 */
public interface ILLMConfigRepository {

    /**
     * 根据ID查询LLM配置
     *
     * @param id LLM配置ID
     * @return LLM配置DTO
     */
    LLMConfigDTO findById(Long id);

    /**
     * 根据模型名称查询LLM配置列表
     *
     * @param modelName  模型名称
     * @param configName 配置名称
     * @param provider   模型提供商
     * @return LLM配置列表
     */
    List<LLMConfigDTO> listByModelNameAndConfigNameAndProvider(String modelName, String configName, LLMProvider provider);

    /**
     * 根据模型名称统计LLM配置数量
     *
     * @param modelName 模型名称
     * @return LLM配置数量
     */
    Long countByModelNameAndConfigNameAndProvider(String modelName, String configName, LLMProvider provider);

    /**
     * 分页查询LLM配置
     *
     * @param current    当前页码
     * @param size       每页数量
     * @param modelName  模型名称
     * @param configName 配置名称
     * @param provider   模型提供商
     * @return LLM配置分页结果
     */
    PageDTO<LLMConfigDTO> pageByModelNameAndConfigNameAndProvider(long current, long size, String modelName, String configName, LLMProvider provider);
}
