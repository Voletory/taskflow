package taskflow.work;

import taskflow.task.AbstractTaskRoutingWrap;
import taskflow.task.TaskRoutingWrap;
import taskflow.work.context.WorkContext;

import java.util.LinkedHashMap;

/**
 * @author steven.zhu 2020/7/6 21:28.
 * @类描述： 序列化执行task，不进行路由
 */
public class SequentialWork extends AbstractWork {
    private boolean executed = false;
    private LinkedHashMap<String, TaskRoutingWrap> tasks;

    public SequentialWork() {
    }

    @Override
    public WorkContext run() {
        if (!executed) {
            executed = true;
            try {
                if (tasks != null && tasks.size() > 0) {
                    tasks.values().forEach((taskRoutingWrap -> {
                        AbstractTaskRoutingWrap absTask = (AbstractTaskRoutingWrap) taskRoutingWrap;
                        absTask.setRouting(null);
                        absTask.doTask(this);
                    }));
                }
            } catch (Exception e) {
                dealException(e);
            }
        }
        return workContext;
    }

    public void setTasks(LinkedHashMap<String, TaskRoutingWrap> tasks) {
        this.tasks = tasks;
    }
}
