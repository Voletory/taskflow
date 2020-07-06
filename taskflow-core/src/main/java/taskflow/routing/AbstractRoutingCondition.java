package taskflow.routing;

import taskflow.task.TaskRoutingWrap;

/**
 * @author steven.zhu 2020/7/4 12:24.
 * @类描述：
 */
public abstract class AbstractRoutingCondition implements RoutingCondition {
    private TaskRoutingWrap taskRoutingWrap;

    @Override
    public TaskRoutingWrap getTaskRoutingWrap() {
        return taskRoutingWrap;
    }

    public void setTaskRoutingWrap(TaskRoutingWrap taskRoutingWrap) {
        this.taskRoutingWrap = taskRoutingWrap;
    }
}
