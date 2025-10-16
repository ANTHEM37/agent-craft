package io.github.anthem37.craft.domain.memory.model.entity;

import cn.hutool.json.JSONUtil;
import io.github.anthem37.craft.domain.common.model.entity.BaseAggregateRoot;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryConfigParams;
import io.github.anthem37.craft.domain.memory.model.value.ChatMemoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天内存配置实体类
 *
 * @author hb28301
 * @date 2025/10/14 10:20:06
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatMemoryConfig extends BaseAggregateRoot {


    /**
     * 配置名称，用于标识不同的记忆配置
     */
    private String chatMemoryConfigName;

    /**
     * 记忆类型
     */
    private ChatMemoryType chatMemoryType;

    /**
     * 记忆参数
     */
    private ChatMemoryConfigParams params;

    /**
     * 记忆描述
     */
    private String description;

    /**
     * 解析记忆参数
     *
     * @param clazz 解析后的内存配置类
     * @return 解析后的内存配置
     */
    public <T extends ChatMemoryConfigParams> T parseParams(Class<T> clazz) {

        return JSONUtil.toBean(JSONUtil.toJsonStr(params), clazz);
    }
}
