package io.github.anthem37.craft.infrastructure.memory.event.listener;

import io.github.anthem37.craft.application.memory.event.BindMemoryChatMemoryConfigEvent;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigRefMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigRefPO;
import io.github.anthem37.easy.ddd.infrastructure.event.AbstractApplicationEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 绑定记忆事件监听器
 *
 * @author hb28301
 * @since 2025/10/20 19:07:44
 */
@Component
@RequiredArgsConstructor
public class BindMemoryChatMemoryConfigEventListener extends AbstractApplicationEventHandler<BindMemoryChatMemoryConfigEvent> {

    private final IChatMemoryConfigRefMapper chatMemoryConfigRefMapper;

    @Override
    protected void doHandle(BindMemoryChatMemoryConfigEvent bindMemoryChatMemoryConfigEvent) {
        Long memoryId = bindMemoryChatMemoryConfigEvent.getMemoryId();
        Long aggregateId = bindMemoryChatMemoryConfigEvent.getAggregateId();
        chatMemoryConfigRefMapper.insert(new ChatMemoryConfigRefPO(aggregateId, memoryId));
    }

    @Override
    public Class<BindMemoryChatMemoryConfigEvent> getSupportedEventType() {

        return BindMemoryChatMemoryConfigEvent.class;
    }

}
