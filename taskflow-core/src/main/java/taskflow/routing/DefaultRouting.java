package taskflow.routing;

import taskflow.task.TaskRoutingWrap;
import taskflow.work.context.WorkContext;

import java.util.List;

/**
 * @author steven.zhu 2020/7/4 12:08.
 * @类描述：
 */
public class DefaultRouting implements Routing {
    private List<RoutingCondition> routingConditions;

    public DefaultRouting() {
    }

    public DefaultRouting(List<RoutingCondition> routingConditions) {
        this.routingConditions = routingConditions;
    }

    @Override
    public TaskRoutingWrap doRouting(WorkContext workContext) {
        for (RoutingCondition routingCondition : routingConditions) {
            if (routingCondition.matched(workContext)) {
                return routingCondition.getTaskRoutingWrap();
            }
        }
        return null;
    }

    public List<RoutingCondition> getRoutingConditions() {
        return routingConditions;
    }

    public void setRoutingConditions(List<RoutingCondition> routingConditions) {
        this.routingConditions = routingConditions;
    }
}
