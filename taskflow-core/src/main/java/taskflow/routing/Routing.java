package taskflow.routing;

import taskflow.task.TaskRoutingWrap;
import taskflow.work.context.WorkContext;

/**
 * @author steven.zhu 2020/7/4 11:06.
 * 当一个{@link taskflow.task.Task}处理完毕后，可以动态确定下一个{@link taskflow.task.Task}
 * 根据定义好的{@link RoutingCondition},根据workContext的值确定下一个{@link taskflow.task.Task}
 *
 */
public interface Routing {
    TaskRoutingWrap doRouting(WorkContext workContext);
}
