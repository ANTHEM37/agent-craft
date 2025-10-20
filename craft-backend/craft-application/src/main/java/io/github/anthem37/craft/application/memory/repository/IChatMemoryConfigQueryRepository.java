package io.github.anthem37.craft.application.memory.repository;


import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;

import java.util.List;

/**
 * 记忆配置仓库接口
 *
 * @author hb28301
 * @since 2025/10/11 15:34:10
 */
public interface IChatMemoryConfigQueryRepository {

    /**
     * 分页查询记忆配置
     *
     * @param id 记忆配置ID
     * @return 记忆配置DTO
     */
    ChatMemoryConfigDTO findById(Long id);

    /**
     * 根据配置名称和记忆类型查询记忆配置列表
     *
     * @param configName     配置名称
     * @param chatMemoryType 记忆类型
     * @return 记忆配置列表
     */
    List<ChatMemoryConfigDTO> listByConfigNameAndChatMemoryType(String configName, ChatMemoryType chatMemoryType);

    /**
     * 根据配置名称和记忆类型统计记忆配置数量
     *
     * @param configName     配置名称
     * @param chatMemoryType 记忆类型
     * @return 记忆配置数量
     */
    Long countByConfigNameAndChatMemoryType(String configName, ChatMemoryType chatMemoryType);

    /**
     * 分页查询记忆配置
     *
     * @param current        当前页码
     * @param size           每页数量
     * @param configName     配置名称
     * @param chatMemoryType 记忆类型
     * @return 记忆配置分页列表
     */
    PageDTO<ChatMemoryConfigDTO> pageByConfigNameAndChatMemoryType(long current, long size, String configName, ChatMemoryType chatMemoryType);

    /**
     * 检查记忆配置是否已绑定到指定记忆实例
     *
     * @param configId 配置ID
     * @param memoryId 记忆ID
     * @return 是否已绑定
     */
    boolean isMemoryBound(Long configId, Long memoryId);
}
