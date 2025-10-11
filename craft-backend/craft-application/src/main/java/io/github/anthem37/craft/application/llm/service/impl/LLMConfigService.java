package io.github.anthem37.craft.application.llm.service.impl;

import io.github.anthem37.craft.application.common.dto.PageDTO;
import io.github.anthem37.craft.application.llm.dto.LLMConfigDTO;
import io.github.anthem37.craft.application.llm.dto.command.CreateLLMConfigCommand;
import io.github.anthem37.craft.application.llm.dto.command.DeleteLLMConfigCommand;
import io.github.anthem37.craft.application.llm.dto.command.UpdateLLMConfigCommand;
import io.github.anthem37.craft.application.llm.dto.query.CountLLMConfigQuery;
import io.github.anthem37.craft.application.llm.dto.query.FindOneLLMConfigQuery;
import io.github.anthem37.craft.application.llm.dto.query.ListLLMConfigQuery;
import io.github.anthem37.craft.application.llm.dto.query.PageLLMConfigQuery;
import io.github.anthem37.craft.application.llm.service.ILLMConfigService;
import io.github.anthem37.easy.ddd.application.AbstractApplicationService;
import io.github.anthem37.easy.ddd.common.cqrs.command.ICommandBus;
import io.github.anthem37.easy.ddd.common.cqrs.query.IQueryBus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * LLM配置服务实现类
 *
 * @author hb28301
 * @date 2025/10/11 16:58:26
 */
@Component
public class LLMConfigService extends AbstractApplicationService implements ILLMConfigService {

    public LLMConfigService(ICommandBus commandBus, IQueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean create(CreateLLMConfigCommand command) {
        return sendCommand(command);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean update(UpdateLLMConfigCommand command) {
        return sendCommand(command);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delete(DeleteLLMConfigCommand command) {
        return sendCommand(command);
    }

    @Override
    public LLMConfigDTO findOne(FindOneLLMConfigQuery query) {
        return sendQuery(query);
    }

    @Override
    public List<LLMConfigDTO> list(ListLLMConfigQuery query) {
        return sendQuery(query);
    }

    @Override
    public Long count(CountLLMConfigQuery query) {
        return sendQuery(query);
    }

    @Override
    public PageDTO<LLMConfigDTO> page(PageLLMConfigQuery query) {
        return sendQuery(query);
    }
}
