package taskflow.boot.starter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskflow.boot.starter.bean.BaseResult;
import taskflow.boot.starter.biz.taskflow.TaskFlowModifyBiz;

import javax.inject.Inject;

/**
 * @author steven.zhu 2020/7/10 22:26.
 * @类描述：
 */
@RestController
@RequestMapping("/taskflow")
public class TaskFlowController {

    @Inject
    private TaskFlowModifyBiz taskFlowModifyBiz;

    @PostMapping("/modify")
    public BaseResult modifyTaskFlow() {
        taskFlowModifyBiz.modifyTaskFlow();
        return new BaseResult();
    }
}
