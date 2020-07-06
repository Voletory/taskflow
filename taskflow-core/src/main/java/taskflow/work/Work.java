package taskflow.work;

import taskflow.task.TaskRoutingWrap;
import taskflow.work.context.TaskTrace;
import taskflow.work.context.WorkContext;

import java.util.List;

/**
 * worker在定义的时候是SCOPE_PROTOTYPE
 *
 * @author steven.zhu 2020/7/2 23:02.
 * @author bailey.fu
 * @author lizhou
 */
public interface Work {

    String getName();
    /**
     * 获取work上下文环境
     *
     * @return
     */
    WorkContext getWorkContext();

    /**
     * 在调用真正的Task业务逻辑之前进行操作,记录调用轨迹
     *
     * @param taskRoutingWrap
     * @throws Exception
     */
    void receive(TaskRoutingWrap taskRoutingWrap) throws Exception;

    /**
     * 返回Task调用轨迹
     *
     * @return
     */
    List<TaskTrace> getTaskRecords();

    /**
     * 加入上下文环境
     *
     * @param key
     * @param input
     */
    void putContext(String key, Object input);

    /**
     * 从上下文获取参数
     *
     * @param key
     * @return
     */
    public <T> T getContext(String key);

    /**
     * 业务开始<br/>
     * 不考虑线程安全,所有Work实现类均以原型模式创建
     *
     * @return
     */
    WorkContext run();

    /**
     * 处理Work执行过程中的异常;也包括各Task执行过程中hold的异常
     *
     * @param workException
     *            Work.run时抛出的异常
     */
    void dealException(Exception workException);

}
