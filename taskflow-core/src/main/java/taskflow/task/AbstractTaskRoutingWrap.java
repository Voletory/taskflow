package taskflow.task;

import taskflow.routing.Routing;
import taskflow.work.Work;

/**
 * @author steven.zhu 2020/7/4 12:50.
 * @类描述：
 */
public abstract class AbstractTaskRoutingWrap implements TaskRoutingWrap {
    private String name;
    private Routing routing;

    public AbstractTaskRoutingWrap() {
    }

    public void doTask(Work work) {
        try {
            work.receive(this);
            invokeTaskMethod(work);
        } catch (Exception e) {
            work.dealException(e);
        }
        if (routing != null) {
            TaskRoutingWrap next = routing.doRouting(work.getWorkContext());
            if (next != null) {
                next.doTask(work);
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Routing getRouting() {
        return routing;
    }

    public void setRouting(Routing routing) {
        this.routing = routing;
    }

    /**
     * 执行task的业务方法
     * @param work
     * @throws Exception
     */
    protected abstract void invokeTaskMethod(Work work) throws Exception;
}
