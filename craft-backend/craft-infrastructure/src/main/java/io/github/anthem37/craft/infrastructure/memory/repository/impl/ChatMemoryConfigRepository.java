package io.github.anthem37.craft.infrastructure.memory.repository.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.memory.dto.ChatMemoryConfigDTO;
import io.github.anthem37.craft.application.memory.respository.IChatMemoryConfigRepository;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import io.github.anthem37.craft.infrastructure.common.po.BasePO;
import io.github.anthem37.craft.infrastructure.memory.converter.ChatMemoryConfigPOConverter;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hb28301
 * @since 2025/10/16 15:19:00
 */
@Component
@RequiredArgsConstructor
public class ChatMemoryConfigRepository implements IChatMemoryConfigRepository {

    private final IChatMemoryConfigMapper chatMemoryConfigMapper;

    @Override
    public ChatMemoryConfigDTO findById(Long id) {

        return ChatMemoryConfigPOConverter.INSTANCE.toDTO(chatMemoryConfigMapper.selectById(id));
    }

    @Override
    public List<ChatMemoryConfigDTO> listByConfigNameAndChatMemoryType(String configName, ChatMemoryType chatMemoryType) {

        return new LambdaQueryChainWrapper<>(chatMemoryConfigMapper)
                .like(StrUtil.isNotBlank(configName), ChatMemoryConfigPO::getConfigName, configName)
                .eq(ObjectUtil.isNotEmpty(chatMemoryType), ChatMemoryConfigPO::getChatMemoryType, chatMemoryType)
                .orderByDesc(BasePO::getUpdatedAt)
                .list()
                .stream()
                .map(ChatMemoryConfigPOConverter.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public Long countByConfigNameAndChatMemoryType(String configName, ChatMemoryType chatMemoryType) {

        return new LambdaQueryChainWrapper<>(chatMemoryConfigMapper)
                .like(StrUtil.isNotBlank(configName), ChatMemoryConfigPO::getConfigName, configName)
                .eq(ObjectUtil.isNotEmpty(chatMemoryType), ChatMemoryConfigPO::getChatMemoryType, chatMemoryType)
                .count();
    }

    @Override
    public PageDTO<ChatMemoryConfigDTO> pageByConfigNameAndChatMemoryType(long current, long size, String configName, ChatMemoryType chatMemoryType) {
        Page<ChatMemoryConfigPO> page = new LambdaQueryChainWrapper<>(chatMemoryConfigMapper)
                .like(StrUtil.isNotBlank(configName), ChatMemoryConfigPO::getConfigName, configName)
                .eq(ObjectUtil.isNotEmpty(chatMemoryType), ChatMemoryConfigPO::getChatMemoryType, chatMemoryType)
                .orderByDesc(BasePO::getUpdatedAt)
                .page(new Page<>(current, size));

        return PageDTO.of(page.getCurrent(), page.getSize(), page.getTotal(),
                page.getRecords().stream().map(ChatMemoryConfigPOConverter.INSTANCE::toDTO).toList());
    }

}
