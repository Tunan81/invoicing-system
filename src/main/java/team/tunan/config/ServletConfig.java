package team.tunan.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author Tunan
 * @version 1.0
 */
public class ServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 加载Spring配置类
     *
     * @return Spring配置类
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    /**
     * 加载SpringMVC配置类
     *
     * @return SpringMVC配置类
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    /**
     * 设置DispatcherServlet的映射路径
     *
     * @return 映射路径
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 设置编码过滤器
     * @return 编码过滤器
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("utf-8");
        return new Filter[]{filter};
    }
}

