package taskflow.task;

import java.lang.annotation.*;

/**
 * Created by lizhou on 2017/5/4/004.
 */
@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Taskparam {
    String value() default "";

    boolean require() default true;
}
