package taskflow.work.context;

import taskflow.work.Work;

import java.util.HashMap;
import java.util.Map;

/**
 * @author steven.zhu 2020/7/3 12:26.
 * 默认实现Context类   默认使用map完成context
 * @类描述：
 */
public class DefaultMapWorkContext extends AbstractWorkContext {
    /**
     * task额外的值，可设定
     */
    private Map<String, Object> context;

    public DefaultMapWorkContext(Class<? extends Work> workClazz) {
        super(workClazz);
        context = new HashMap<>();
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    @Override
    public Object get(String parameterName) {
        return context.get(parameterName);
    }

    @Override
    public void put(String key, Object value) {
        context.put(key, value);
    }

    @Override
    public String toString() {
        return "MapWorkContext [context=" + context + ", " + super.toString() + "]";
    }

}
