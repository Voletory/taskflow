package taskflow.task;

import taskflow.exception.TaskFlowException;
import taskflow.work.Work;

/**
 * @author steven.zhu 2020/7/4 16:59.
 * @类描述：
 */
public class ReflectedTaskRoutingWrap extends AbstractTaskRoutingWrap {
  private TaskMethodInvoker taskMethodInvoker;
    @Override
    protected void invokeTaskMethod(Work work) throws Exception {
        if (taskMethodInvoker != null) {
            taskMethodInvoker.invokeTask(work);
        } else {
            throw new TaskFlowException("TaskMethodInvoker is null,No task be executed !");
        }
    }

    public TaskMethodInvoker getTaskMethodInvoker() {
        return taskMethodInvoker;
    }

    public void setTaskMethodInvoker(TaskMethodInvoker taskMethodInvoker) {
        this.taskMethodInvoker = taskMethodInvoker;
    }
}
