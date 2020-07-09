package taskflow.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import taskflow.exception.TaskFlowException;
import taskflow.task.TaskRoutingWrap;
import taskflow.work.context.AbstractWorkContext;
import taskflow.work.context.DefaultMapWorkContext;
import taskflow.work.context.TaskTrace;
import taskflow.work.context.WorkContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steven.zhu 2020/7/3 12:31.
 * work的抽象类
 * @类描述：
 */
public abstract class AbstractWork implements Work {
    private Logger logger = LoggerFactory.getLogger(Work.class);
    /**
     * work_name
     */
    protected String name;
    protected int maxTasks;
    protected int executedTasks;
    protected WorkContext workContext;
    /**
     * work的task快照
     */
    protected List<TaskTrace> taskRecords;
    private boolean traceable;

    public AbstractWork() {
        workContext = new DefaultMapWorkContext(this.getClass());
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void dealException(Exception workException) {
        workContext.holderException(((AbstractWorkContext)workContext).getCurrentTask(),workException);
        if (logger.isErrorEnabled()) {
            logger.error("work:" + name,workException);
        }
    }

    /**
     * 记录调用轨迹，并检查任务调用次数
     * @param taskRoutingWrap
     */
    @Override
    public void receive(TaskRoutingWrap taskRoutingWrap) {
        ((AbstractWorkContext)workContext).setCurrentTask(taskRoutingWrap.getName());
        if (maxTasks <= executedTasks++) {
            throw new TaskFlowException("max tasks is:" + maxTasks);
        }
        if (traceable) {
            if (taskRecords ==null) {
             taskRecords = new ArrayList<>();
            }
            taskRecords.add(new TaskTrace(taskRoutingWrap.getName(), workContext.toString()));
        }
    }

    public void setTraceable(boolean traceable) {
        this.traceable = traceable;
    }

    public List<TaskTrace> getTaskRecords() {
        return taskRecords;
    }

    @Override
    public WorkContext getWorkContext() {
        return workContext;
    }

    public int getMaxTasks() {
        return maxTasks;
    }

    public void setMaxTasks(int maxTasks) {
        this.maxTasks = maxTasks;
    }

    public int getExecutedTasks() {
        return executedTasks;
    }

    @Override
    public void putContext(String key, Object value) {
        workContext.put(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getContext(String key) {
        return (T)workContext.get(key);
    }

    public void setWorkContext(WorkContext workContext) {
        this.workContext = workContext;
    }
    public void setRoutingKey(String key) {
        workContext.setRoutingKey(key);
    }

}
