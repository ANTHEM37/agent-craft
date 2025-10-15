package io.github.anthem37.craft.infrastructure.memory.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.anthem37.craft.infrastructure.memory.mybatis.po.ChatMessagePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hb28301
 */
@Mapper
public interface IChatMessageMapper extends BaseMapper<ChatMessagePO> {
}