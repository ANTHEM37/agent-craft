package io.github.anthem37.craft.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动入口
 *
 * @author hb28301
 */
@SpringBootApplication(scanBasePackages = "io.github.anthem37.craft")
public class CraftBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CraftBackendApplication.class, args);
    }
}