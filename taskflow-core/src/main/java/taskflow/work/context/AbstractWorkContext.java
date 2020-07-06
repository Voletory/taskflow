package taskflow.work.context;

import taskflow.work.Work;

import java.util.HashMap;
import java.util.Map;

/**
 * @author steven.zhu 2020/7/2 23:45.
 * @类描述：
 */
public abstract class AbstractWorkContext implements WorkContext {
    /**
     * 上一个task
     */
    private String previousTask;
    /**
     * 当前task
     */
    private String currentTask;
    /**
     * 路由值
     */
    private String routingKey;
    /**
     * work结果
     */
    private Object result;

    /**
     * 异常
     */
    private Map<String, Exception> exceptions;

    private Class<? extends Work> workClazz;

    public AbstractWorkContext(Class<? extends Work> workClazz) {
        this.workClazz = workClazz;
    }

    public void setCurrentTask(String currentTask) {
        this.previousTask = this.currentTask;
        this.currentTask = previousTask;
    }

    @Override
    public void holderException(String taskName, Exception exception) {
        if (exceptions == null) {
            exceptions = new HashMap<>();
        }
        exceptions.put(taskName, exception);
    }

    @Override
    public String getRoutingKey() {
        return routingKey;
    }

    @Override
    public <T> T getResult() {
        return (T) result;
    }

    @Override
    public void setRoutingKey(String key) {
        this.routingKey = key;
    }

    @Override
    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public Map<String, Exception> getException() {
        return exceptions;
    }
}
