package team.tunan.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * This class is an Aspect Oriented Programming (AOP) interceptor for logging requests and responses.
 * It logs the start and end of each request, including the request id, path, IP, parameters, and the total time taken.
 * The logging is done using the SLF4J logging facade.
 * The interceptor is applied to all methods in the 'team.tunan.controller' package.
 *
 * @author <a href="https://gitee.com/xia-haike">图南</a>
 */
@Slf4j
@Aspect
@Component
public class LogInterceptor {

    /**
     * This method is Around advice that intercepts all methods in the 'team.tunan.controller' package.
     * It logs the start and end of each request, including the request id, path, IP, parameters, and the total time taken.
     * The logging is done using the SLF4J logging facade.
     *
     * @param point The ProceedingJoinPoint that represents the method being intercepted.
     * @return The result of the method being intercepted.
     * @throws Throwable If an error occurs during the execution of the method being intercepted.
     */
    @Around("execution(* team.tunan.controller.*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint point) throws Throwable {
        // Start the stopwatch for timing
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // Get the request path
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        // Generate a unique id for the request
        String requestId = UUID.randomUUID().toString();
        String url = httpServletRequest.getRequestURI();
        // Get the request parameters
        Object[] args = point.getArgs();
        String reqParam = "[" + StringUtils.join(args, ", ") + "]";
        // Log the start of the request
        log.info("request start，id: {}, path: {}, ip: {}, params: {}", requestId, url,
                httpServletRequest.getRemoteHost(), reqParam);
        // Execute the original method
        Object result = point.proceed();
        // Log the end of the request
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        log.info("request end, id: {}, cost: {}ms", requestId, totalTimeMillis);
        return result;
    }
}