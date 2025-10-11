package io.github.anthem37.craft.infrastructure.llm.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.anthem37.craft.infrastructure.llm.mybatis.po.LLMConfigPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hb28301
 */
@Mapper
public interface ILLMConfigMapper extends BaseMapper<LLMConfigPO> {
}