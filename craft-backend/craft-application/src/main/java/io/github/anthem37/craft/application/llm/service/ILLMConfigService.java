package io.github.anthem37.craft.application.llm.service;

import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.llm.cqrs.command.CreateLLMConfigCommand;
import io.github.anthem37.craft.application.llm.cqrs.command.DeleteLLMConfigCommand;
import io.github.anthem37.craft.application.llm.cqrs.command.UpdateLLMConfigCommand;
import io.github.anthem37.craft.application.llm.cqrs.query.CountLLMConfigQuery;
import io.github.anthem37.craft.application.llm.cqrs.query.FindOneLLMConfigQuery;
import io.github.anthem37.craft.application.llm.cqrs.query.ListLLMConfigQuery;
import io.github.anthem37.craft.application.llm.cqrs.query.PageLLMConfigQuery;
import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;

import java.util.List;

/**
 * @author hb28301
 * @since 2025/10/11 16:44:04
 */
public interface ILLMConfigService {

    /**
     * 创建LLM配置
     *
     * @param command 创建LLM配置命令
     * @return 是否创建成功
     */
    Boolean create(CreateLLMConfigCommand command);

    /**
     * 更新LLM配置
     *
     * @param command 更新LLM配置命令
     * @return 是否更新成功
     */
    Boolean update(UpdateLLMConfigCommand command);

    /**
     * 删除LLM配置
     *
     * @param command 删除LLM配置命令
     * @return 是否删除成功
     */
    Boolean delete(DeleteLLMConfigCommand command);

    /**
     * 查询LLM配置
     *
     * @param query 查询LLM配置查询
     * @return LLM配置DTO
     */
    LLMConfigDTO findOne(FindOneLLMConfigQuery query);

    /**
     * 查询所有LLM配置
     *
     * @return LLM配置DTO列表
     */
    List<LLMConfigDTO> list(ListLLMConfigQuery query);

    /**
     * 统计LLM配置数量
     *
     * @param query 统计LLM配置查询
     * @return LLM配置数量
     */
    Long count(CountLLMConfigQuery query);

    /**
     * 分页查询LLM配置
     *
     * @param query 分页查询LLM配置查询
     * @return LLM配置DTO分页
     */
    PageDTO<LLMConfigDTO> page(PageLLMConfigQuery query);
}
