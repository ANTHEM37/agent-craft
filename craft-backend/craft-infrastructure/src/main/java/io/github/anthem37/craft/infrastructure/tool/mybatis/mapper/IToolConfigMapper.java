package io.github.anthem37.craft.infrastructure.tool.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.anthem37.craft.infrastructure.tool.mybatis.po.ToolConfigPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工具配置Mapper
 */
@Mapper
public interface IToolConfigMapper extends BaseMapper<ToolConfigPO> {
}