package taskflow.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import taskflow.work.Work;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author steven.zhu 2020/7/4 16:59.
 * @类描述：
 */
public class TaskMethodInvoker {

    protected final Logger logger = LoggerFactory.getLogger(TaskMethodInvoker.class);

    private final Object bean;
    private final Class<?> beanType;
    private Method method;
    private final Method bridgedMethod;
    private final MethodParameter[] methodParameters;

    public TaskMethodInvoker(Object bean, String methodName) {
        Assert.notNull(bean, "Bean is required");
        Assert.notNull(methodName, "method name is required");
        this.bean = bean;
        this.beanType = ClassUtils.getUserClass(bean);
        // 修改通过method的sign来判断一致
        for (Method tempMethod : bean.getClass().getMethods()) {
            if (tempMethod.getName().equals(methodName)) {
                if (this.method != null) {
                    throw new RuntimeException("class:" + bean.getClass() + "have more than one method:" + methodName);
                }
                this.method = tempMethod;
            }
        }
        if (this.method == null) {
            throw new RuntimeException("class:" + bean.getClass() + "have not method:" + methodName);
        }
        this.bridgedMethod = BridgeMethodResolver.findBridgedMethod(this.method);
        this.methodParameters = initMethodParameters();
    }

    public void invokeTask(Work work) throws Exception {
        Object[] agrs = TaskParameterResolver.resolve(methodParameters, work);
        doInvoke(agrs);
    }

    /**
     * Invoke the handler method with the given argument values.
     */
    protected void doInvoke(Object... args) throws Exception {
        ReflectionUtils.makeAccessible(getBridgedMethod());
        try {
            getBridgedMethod().invoke(bean, args);
        } catch (IllegalArgumentException ex) {
            String text = (ex.getMessage() != null ? ex.getMessage() : "Illegal argument");
            throw new IllegalStateException(ex);
        } catch (InvocationTargetException ex) {
            // Unwrap for HandlerExceptionResolvers ...
            Throwable targetException = ex.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw (RuntimeException) targetException;
            } else if (targetException instanceof Error) {
                throw (Error) targetException;
            } else if (targetException instanceof Exception) {
                throw (Exception) targetException;
            } else {
                String text = "Failed to invoke handler method";
                throw new IllegalStateException(text, targetException);
            }
        }
    }

    private MethodParameter[] initMethodParameters() {
        int count = this.bridgedMethod.getParameterTypes().length;
        MethodParameter[] result = new MethodParameter[count];
        for (int i = 0; i < count; i++) {
            HandlerMethodParameter parameter = new HandlerMethodParameter(i);
            GenericTypeResolver.resolveParameterType(parameter, this.beanType);
            result[i] = parameter;
        }
        return result;
    }

    protected Method getBridgedMethod() {
        return this.bridgedMethod;
    }

    public <A extends Annotation> A getMethodAnnotation(Class<A> annotationType) {
        return AnnotatedElementUtils.findMergedAnnotation(this.method, annotationType);
    }

    public <A extends Annotation> boolean hasMethodAnnotation(Class<A> annotationType) {
        return AnnotatedElementUtils.hasAnnotation(this.method, annotationType);
    }

    public Class<?> getBeanType() {
        return this.beanType;
    }

    /**
     * A MethodParameter with BusHandlerMethod-specific behavior.
     */
    protected class HandlerMethodParameter extends SynthesizingMethodParameter {

        public HandlerMethodParameter(int index) {
            super(TaskMethodInvoker.this.bridgedMethod, index);
        }

        protected HandlerMethodParameter(HandlerMethodParameter original) {
            super(original);
        }

        @Override
        public Class<?> getContainingClass() {
            return TaskMethodInvoker.this.getBeanType();
        }

        @Override
        public <T extends Annotation> T getMethodAnnotation(Class<T> annotationType) {
            return TaskMethodInvoker.this.getMethodAnnotation(annotationType);
        }

        @Override
        public <T extends Annotation> boolean hasMethodAnnotation(Class<T> annotationType) {
            return TaskMethodInvoker.this.hasMethodAnnotation(annotationType);
        }

        @Override
        public HandlerMethodParameter clone() {
            return new HandlerMethodParameter(this);
        }
    }
}
