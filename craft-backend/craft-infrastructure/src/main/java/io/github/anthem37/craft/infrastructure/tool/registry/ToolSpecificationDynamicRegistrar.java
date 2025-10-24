package io.github.anthem37.craft.infrastructure.tool.registry;

import io.github.anthem37.craft.domain.tool.model.entity.ToolConfig;
import io.github.anthem37.craft.infrastructure.tool.assembler.persistence.ToolConfigPersistenceAssembler;
import io.github.anthem37.craft.infrastructure.tool.factory.ToolSpecificationFactory;
import io.github.anthem37.craft.infrastructure.tool.mybatis.mapper.IToolConfigMapper;
import io.github.anthem37.craft.infrastructure.tool.mybatis.po.ToolConfigPO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 启动后动态注册 ToolSpecification Bean
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ToolSpecificationDynamicRegistrar implements SmartInitializingSingleton {

    private final IToolConfigMapper toolConfigMapper;
    private final ToolSpecificationFactory toolSpecificationFactory;
    private final ConfigurableListableBeanFactory beanFactory;

    @Override
    public void afterSingletonsInstantiated() {
        try {
            List<ToolConfigPO> pos = toolConfigMapper.selectList(null);
            for (ToolConfigPO po : pos) {
                ToolConfig toolConfig = ToolConfigPersistenceAssembler.INSTANCE.toDomain(po);
                String beanName = toolConfig.getBeanName();

                if (beanFactory.containsSingleton(beanName)) {
                    log.warn("ToolSpecification bean '{}' 已存在，跳过动态注册", beanName);
                    continue;
                }

                var spec = toolSpecificationFactory.build(toolConfig);
                beanFactory.registerSingleton(beanName, spec);
                log.info("已动态注册 ToolSpecification bean: {}", beanName);
            }
        } catch (Exception e) {
            log.error("动态注册 ToolSpecification 失败", e);
        }
    }
}