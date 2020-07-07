package taskflow.work;

import taskflow.task.TaskRoutingWrap;
import taskflow.work.context.WorkContext;

/**
 * @author steven.zhu 2020/7/4 10:36.
 * @类描述：
 */
public class DefaultConditionRouteWork extends AbstractWork {
    // 初始任务；一般在次初始化一些参数
    private TaskRoutingWrap start;
    // 结束任务；无论是否正常完成，该任务都会执行;可再次封装返回结果
    private TaskRoutingWrap finish;

    @Override
    public WorkContext run() {
        try {
            start.doTask(this);
        } catch (Exception e) {
            dealException(e);
        } finally {
            if (finish != null) {
                finish.doTask(this);
            }
        }
        return workContext;
    }

    public void setStart(TaskRoutingWrap start) {
        this.start = start;
    }

    public void setFinish(TaskRoutingWrap finish) {
        this.finish = finish;
    }

}
