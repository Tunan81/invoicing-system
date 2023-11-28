package team.tunan.config;


import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/21
 */
@Configuration
@MapperScan("team.tunan.mapper")
public class MyBatisConfig {

    /**
     * 获取MybatisSqlSessionFactoryBean
     *
     * @return MybatisSqlSessionFactoryBean
     */
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dataSource);
        return mybatisPlus;
    }

//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer msc = new MapperScannerConfigurer();
//        msc.setBasePackage("team.tunan.mapper");
//        return msc;
//    }
}
