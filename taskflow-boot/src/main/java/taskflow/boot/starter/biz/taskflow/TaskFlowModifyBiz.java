package taskflow.boot.starter.biz.taskflow;

import org.springframework.beans.factory.annotation.Autowired;
import taskflow.config.TaskFlowBeanReloadProcessor;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author steven.zhu 2020/7/10 22:25.
 * @类描述：
 */
@Named("taskFlowModifyBiz")
public class TaskFlowModifyBiz {


    @Inject
    private TaskFlowBeanReloadProcessor taskFlowBeanReloadProcessor;

    public void modifyTaskFlow() {
//        taskFlowBeanReloadProcessor.reload();
        System.out.println("change");
    }
}
