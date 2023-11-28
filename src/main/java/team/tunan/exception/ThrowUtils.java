package team.tunan.exception;

import team.tunan.common.HttpCodeEnum;


/**
 * 抛异常工具类
 *
 * @author <a href="https://gitee.com/xia-haike">图南</a>
 */
public class ThrowUtils {

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     */
    public static void throwIf(boolean condition, HttpCodeEnum httpCodeEnum) {
        throwIf(condition, new BusinessException(httpCodeEnum));
    }

    /**
     * 条件成立则抛异常
     */
    public static void throwIf(boolean condition, HttpCodeEnum httpCodeEnum, String message) {
        throwIf(condition, new BusinessException(httpCodeEnum, message));
    }
}
