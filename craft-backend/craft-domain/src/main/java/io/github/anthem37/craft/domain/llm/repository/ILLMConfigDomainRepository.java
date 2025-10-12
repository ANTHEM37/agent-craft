package io.github.anthem37.craft.domain.llm.repository;

import io.github.anthem37.craft.domain.llm.model.entity.LLMConfig;
import io.github.anthem37.easy.ddd.domain.repository.IDomainRepository;

/**
 * LLM配置域仓储接口
 *
 * @author hb28301
 * @since 2025/10/11 15:21:52
 */
public interface ILLMConfigDomainRepository extends IDomainRepository<LLMConfig, Long> {
}
