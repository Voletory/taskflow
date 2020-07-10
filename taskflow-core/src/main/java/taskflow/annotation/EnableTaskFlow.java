package taskflow.annotation;

import org.springframework.context.annotation.Import;
import taskflow.config.ModeImportSelector;
import taskflow.config.TaskFlowConfiguration;
import taskflow.enums.ConfigSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that enables Taskflow by annotating Configuration classes. Only one occurrence of this annotation is needed.
 * @author steven.zhu 2020/7/7 21:50.
 * @类描述：
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(ModeImportSelector.class)
public @interface EnableTaskFlow {

    ConfigSource source() default ConfigSource.XML;

}
