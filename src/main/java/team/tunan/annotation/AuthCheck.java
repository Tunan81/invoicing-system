package team.tunan.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used for authorization checks on methods.
 * It can be used to specify a role that a user must have to access the annotated method.
 * If no role is specified, the annotation will not have any effect.
 *
 * @author <a href="https://gitee.com/xia-haike">图南</a>
 */
@Target(ElementType.METHOD) // This annotation can be used on methods only.
@Retention(RetentionPolicy.RUNTIME) // This annotation will be available at runtime.
public @interface AuthCheck {

    /**
     * Specifies the role that a user must have to access the annotated method.
     * If no role is specified, the annotation will not have any effect.
     *
     * @return The role that a user must have.
     */
    String mustRole() default "";

}