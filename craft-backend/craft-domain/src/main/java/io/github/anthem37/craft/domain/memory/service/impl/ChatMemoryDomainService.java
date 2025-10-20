package io.github.anthem37.craft.domain.memory.service.impl;

import io.github.anthem37.craft.domain.memory.repository.IChatMemoryConfigDomainRepository;
import io.github.anthem37.craft.domain.memory.service.IChatMemoryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天记忆领域服务实现
 *
 * @author hb28301
 * @since 2025/10/17 15:00:00
 */
@Slf4j
@RequiredArgsConstructor
public class ChatMemoryDomainService implements IChatMemoryDomainService {

    private final IChatMemoryConfigDomainRepository chatMemoryConfigDomainRepository;
}