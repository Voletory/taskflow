package taskflow.task;

import taskflow.work.Work;

/**
 * @author steven.zhu 2020/7/4 11:15.
 * @类描述：
 * 一个Task可以看成是一个单独的业务处理逻辑<br/>
 * 利用责任链模式，把一组Task的组合看成责任链中的一个节点，通过routing来确定下一个节点
 */
@Deprecated
public interface Task {

    /**
     * 执行业务逻辑
     * 参数和执行结果均放入work的WorkContext
     *
     * @param work
     */
    void execute(Work work);
}
