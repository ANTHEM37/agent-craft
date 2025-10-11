package io.github.anthem37.craft.infrastructure.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * Mybatis Plus 配置
 *
 * @author hb28301
 * @date 2025/10/11 16:32:11
 */
@Configuration
@MapperScan("io.github.anthem37.craft.infrastructure.**.mapper")
public class MyMybatisPlusConfig {

    /**
     * 自动填充创建时间和更新时间
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                setFieldValByName("createdAt", LocalDateTime.now(), metaObject);
                setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
            }
        };
    }

}
