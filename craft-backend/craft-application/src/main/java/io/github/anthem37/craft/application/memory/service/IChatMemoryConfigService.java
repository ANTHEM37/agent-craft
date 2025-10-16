package io.github.anthem37.craft.application.memory.service;

import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.application.memory.dto.command.CreateChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.dto.command.DeleteChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.dto.command.UpdateChatMemoryConfigCommand;
import io.github.anthem37.craft.application.memory.dto.query.CountChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.dto.query.FindOneChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.dto.query.ListChatMemoryConfigQuery;
import io.github.anthem37.craft.application.memory.dto.query.PageChatMemoryConfigQuery;

import java.util.List;

/**
 * @author hb28301
 * @since 2025/10/11 16:44:04
 */
public interface IChatMemoryConfigService {

    /**
     * 创建记忆配置
     *
     * @param command 创建记忆配置命令
     * @return 是否创建成功
     */
    Boolean create(CreateChatMemoryConfigCommand command);

    /**
     * 更新记忆配置
     *
     * @param command 更新记忆配置命令
     * @return 是否更新成功
     */
    Boolean update(UpdateChatMemoryConfigCommand command);

    /**
     * 删除记忆配置
     *
     * @param command 删除记忆配置命令
     * @return 是否删除成功
     */
    Boolean delete(DeleteChatMemoryConfigCommand command);

    /**
     * 查询记忆配置
     *
     * @param query 查询记忆配置查询
     * @return 记忆配置DTO
     */
    ChatMemoryConfigDTO findOne(FindOneChatMemoryConfigQuery query);

    /**
     * 查询所有记忆配置
     *
     * @return 记忆配置DTO列表
     */
    List<ChatMemoryConfigDTO> list(ListChatMemoryConfigQuery query);

    /**
     * 统计记忆配置数量
     *
     * @param query 统计记忆配置查询
     * @return 记忆配置数量
     */
    Long count(CountChatMemoryConfigQuery query);

    /**
     * 分页查询记忆配置
     *
     * @param query 分页查询记忆配置查询
     * @return 记忆配置DTO分页
     */
    PageDTO<ChatMemoryConfigDTO> page(PageChatMemoryConfigQuery query);
}
