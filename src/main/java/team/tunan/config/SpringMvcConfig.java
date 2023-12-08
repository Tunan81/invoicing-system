package team.tunan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Tunan
 * @version 1.0
 */
@EnableWebMvc //开启json数据类型自动转换
@Configuration
@ComponentScan({"team.tunan.controller","team.tunan.config"})
public class SpringMvcConfig {
}
