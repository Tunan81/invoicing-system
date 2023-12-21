package team.tunan.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import team.tunan.annotation.AuthCheck;
import team.tunan.common.HttpCodeEnum;
import team.tunan.exception.BusinessException;
import team.tunan.model.entity.User;
import team.tunan.model.enums.UserRoleEnum;
import team.tunan.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * This class is an Aspect Oriented Programming (AOP) interceptor for authorization checks.
 * It uses the AuthCheck annotation to determine if a user has the necessary role to access a method.
 * If the user does not have the necessary role, a BusinessException is thrown.
 * If the user has the necessary role, the method is allowed to proceed.
 *
 * @author <a href="https://gitee.com/xia-haike">图南</a>
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * This method is Around advice that intercepts methods annotated with AuthCheck.
     * It checks if the logged-in user has the necessary role to access the method.
     * If the user does not have the necessary role, a BusinessException is thrown.
     * If the user has the necessary role, the method is allowed to proceed.
     *
     * @param joinPoint The ProceedingJoinPoint that represents the method being intercepted.
     * @param authCheck The AuthCheck annotation on the method being intercepted.
     * @return The result of the method being intercepted if the user has the necessary role.
     * @throws Throwable If an error occurs during the execution of the method being intercepted.
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // The logged-in user
        User loginUser = userService.getLoginUser(request);
        // The user must have this role to access the method
        if (StringUtils.isNotBlank(mustRole)) {
            UserRoleEnum mustUserRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
            if (mustUserRoleEnum == null) {
                throw new BusinessException(HttpCodeEnum.NO_AUTH_ERROR);
            }
            String userRole = loginUser.getUserRole();
            // If the user is banned, access is denied
            if (UserRoleEnum.BAN.equals(mustUserRoleEnum)) {
                throw new BusinessException(HttpCodeEnum.NO_AUTH_ERROR);
            }

            // The user must have admin role
            if (UserRoleEnum.ADMIN.equals(mustUserRoleEnum)) {
                if (!mustRole.equals(userRole)) {
                    throw new BusinessException(HttpCodeEnum.NO_AUTH_ERROR);
                }
            }
            if (UserRoleEnum.SALESMAN.equals(mustUserRoleEnum)) {
                if (!mustRole.equals(userRole) || !userRole.equals(UserRoleEnum.ADMIN.getValue())) {
                    throw new BusinessException(HttpCodeEnum.NO_AUTH_ERROR);
                }
            }
            if (UserRoleEnum.BUYER.equals(mustUserRoleEnum)) {
                if (!mustRole.equals(userRole) || !userRole.equals(UserRoleEnum.ADMIN.getValue())) {
                    throw new BusinessException(HttpCodeEnum.NO_AUTH_ERROR);
                }
            }
        }
        // If the user has the necessary role, the method is allowed to proceed
        return joinPoint.proceed();
    }
}