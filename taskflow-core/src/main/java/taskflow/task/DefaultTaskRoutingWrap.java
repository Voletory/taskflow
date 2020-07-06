package taskflow.task;

import taskflow.work.Work;

/**
 * @author steven.zhu 2020/7/4 12:55.
 * @类描述：
 */
@Deprecated
public class DefaultTaskRoutingWrap extends AbstractTaskRoutingWrap {
    private Task task;

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    protected void invokeTaskMethod(Work work) throws Exception {
        task.execute(work);
    }
}
