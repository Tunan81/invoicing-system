package team.tunan.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/23
 */
@Configuration
@ComponentScan({"team.tunan.service","team.tunan.exception","team.tunan.aop"})
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class,MyBatisConfig.class})
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SpringConfig {
}
