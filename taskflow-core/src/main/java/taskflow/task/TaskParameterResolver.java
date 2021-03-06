package taskflow.task;


import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.StringUtils;
import taskflow.work.Work;
import taskflow.work.context.WorkContext;

import java.util.HashMap;
import java.util.Map;

public class TaskParameterResolver {
    private static ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private static final DefaultValue DEFAULT_VALUE = new DefaultValue();
    private static final Map<?,Object> primitiveDefaultValueMap = new HashMap<Object,Object>() {
		private static final long serialVersionUID = 8334474156660330551L;
	{
        put(boolean.class, DEFAULT_VALUE.defaultBoolean);
        put(byte.class, DEFAULT_VALUE.defaultByte);
        put(char.class, DEFAULT_VALUE.defaultChar);
        put(double.class, DEFAULT_VALUE.defaultDouble);
        put(float.class, DEFAULT_VALUE.defaultFloat);
        put(int.class, DEFAULT_VALUE.defaultInt);
        put(long.class, DEFAULT_VALUE.defaultLong);
        put(short.class, DEFAULT_VALUE.defaultShort);
    }};
    private static final Map<Class<?>,Class<?>> wrappedTypeMap=new HashMap<Class<?>,Class<?>>() {
		private static final long serialVersionUID = 1L;
		{
			put(boolean.class, Boolean.class);
	        put(byte.class, Byte.class);
	        put(char.class, Character.class);
	        put(double.class, Double.class);
	        put(float.class, Float.class);
	        put(int.class, Integer.class);
	        put(long.class, Long.class);
	        put(short.class, Short.class);
		}
    };

    public static Object[] resolve(MethodParameter[] parameters, Work work) {
        Object[] res = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            MethodParameter methodParameter = parameters[i];
            methodParameter.initParameterNameDiscovery(parameterNameDiscoverer);
            if (methodParameter.getParameterType().isAssignableFrom(Work.class)) {
                res[i] = work;
                continue;
            } else if (methodParameter.getParameterType().isAssignableFrom(WorkContext.class)) {
                res[i] = work.getWorkContext();
                continue;
            }
            String parameterName = methodParameter.getParameterName();
            boolean requeire = false;
            if (methodParameter.getParameterAnnotation(Taskparam.class) != null) {
                String parameterNameTemp = methodParameter.getParameterAnnotation(Taskparam.class).value();
                if (!StringUtils.isEmpty(parameterNameTemp)) {
                    parameterName = parameterNameTemp;
                }
                requeire = methodParameter.getParameterAnnotation(Taskparam.class).require();
            }
            Object candicate = work.getContext(parameterName);
            if (candicate == null) {
                if (requeire) {
                    throw new IllegalArgumentException("can not resolve parameter:" + parameterName);
                } else {
                    candicate = nullValue(methodParameter.getParameterType());
                }
			} else {
				Class<?> parameterType = methodParameter.getParameterType();
				parameterType = parameterType.isPrimitive() ? wrappedTypeMap.get(parameterType) : parameterType;
				if (!parameterType.isAssignableFrom(candicate.getClass())) {
					throw new IllegalArgumentException("parameter:" + parameterName + "'s type error");
				}
			}
            res[i] = candicate;
        }
        return res;
    }

    private static Object nullValue(Class<?> parameterType) {
    	//原始类型
        if (parameterType.isPrimitive()) {
            return primitiveDefaultValueMap.get(parameterType);
        }
        return null;
    }

    final private static class DefaultValue {
        private byte defaultByte;
        private char defaultChar;
        private boolean defaultBoolean;
        private double defaultDouble;
        private float defaultFloat;
        private int defaultInt;
        private long defaultLong;
        private short defaultShort;
    }


}