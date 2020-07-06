package taskflow.routing;

import taskflow.task.TaskRoutingWrap;
import taskflow.work.context.WorkContext;

/**
 * @author steven.zhu 2020/7/4 11:14.
 * @类描述：路由条件，现在只支持根据{@link taskflow.work.context.WorkContext}中的routingKey进行路由
 */
public interface RoutingCondition {
    /**
     * 是否匹配workContext中指定的routing
     * @param workContext
     * @return
     */
    boolean matched(WorkContext workContext);

    /**
     * 当前路由对应的taskWrap
     * @return
     */
    TaskRoutingWrap getTaskRoutingWrap();
}
