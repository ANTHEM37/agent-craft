package io.github.anthem37.craft.application.llm.repository;


import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;

import java.util.List;

/**
 * LLM配置仓库接口
 *
 * @author hb28301
 * @date 2025/10/11 15:34:10
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
     * @param modelName 模型名称
     * @return LLM配置列表
     */
    List<LLMConfigDTO> listByModelName(String modelName);

    /**
     * 根据模型名称统计LLM配置数量
     *
     * @param modelName 模型名称
     * @return LLM配置数量
     */
    Long countByModelName(String modelName);
}
