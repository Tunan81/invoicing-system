package team.tunan.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/12/9
 */
@SuppressWarnings("all")
public class SessionTimeoutInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        // 设置Session的过期时间为30天，单位是秒
        session.setMaxInactiveInterval(30 * 24 * 60 * 60);
        return true;
    }
}
