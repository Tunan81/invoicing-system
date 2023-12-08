package team.tunan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team.tunan.interceptor.SessionTimeoutInterceptor;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/12/9
 * @description Session配置类
 */
@Configuration
public class SessionConfig implements WebMvcConfigurer {

    /**
     * 设置拦截器
     *
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置拦截路径
        registry.addInterceptor(new SessionTimeoutInterceptor()).addPathPatterns("/**");
    }
}
