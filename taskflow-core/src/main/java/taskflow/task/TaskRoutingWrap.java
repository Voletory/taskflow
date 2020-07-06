package taskflow.task;

import taskflow.work.Work;

/**
 * @author steven.zhu 2020/7/4 10:23.
 * @类描述：执行task逻辑并routing到下一个task
 * task和rout功能封装类
 */
public interface TaskRoutingWrap {
    String getName();

    void doTask(Work work);
}
