package team.tunan.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.tunan.common.HttpCodeEnum;
import team.tunan.common.Result;

/**
 * 全局异常处理器
 *
 * @author <a href="https://github.com/Tunan81">图南</a>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        System.out.println(111111111);
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        System.out.println(111111111);
        return Result.fail(HttpCodeEnum.SYSTEM_ERROR.getCode(), "系统错误");
    }
}
