package io.github.anthem37.craft.infrastructure.memory.event.listener;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import io.github.anthem37.craft.domain.memory.event.UnbindMemoryChatMemoryConfigEvent;
import io.github.anthem37.craft.infrastructure.memory.mybatis.mapper.IChatMemoryConfigRefMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMemoryConfigRefPO;
import io.github.anthem37.easy.ddd.domain.event.IEventHandler;
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
public class UnbindMemoryChatMemoryConfigEventListener implements IEventHandler<UnbindMemoryChatMemoryConfigEvent> {

    private final IChatMemoryConfigRefMapper chatMemoryConfigRefMapper;

    @Override
    public void handle(UnbindMemoryChatMemoryConfigEvent unbindMemoryChatMemoryConfigEvent) {
        Long memoryId = unbindMemoryChatMemoryConfigEvent.getMemoryId();
        Long aggregateId = unbindMemoryChatMemoryConfigEvent.getAggregateId();
        new LambdaUpdateChainWrapper<>(chatMemoryConfigRefMapper)
                .eq(ChatMemoryConfigRefPO::getConfigId, aggregateId)
                .eq(ChatMemoryConfigRefPO::getMemoryId, memoryId)
                .remove();
    }

    @Override
    public Class<UnbindMemoryChatMemoryConfigEvent> getSupportedEventType() {

        return UnbindMemoryChatMemoryConfigEvent.class;
    }

}
