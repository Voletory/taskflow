package taskflow.boot.starter.biz.taskflow;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import taskflow.config.TaskFlowBeanReloadProcessor;
import taskflow.config.bean.WorkDefinition;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Set;

/**
 * @author steven.zhu 2020/7/10 22:25.
 * @类描述：
 */
@Named("taskFlowModifyBiz")
public class TaskFlowModifyBiz {


    @Inject
    private TaskFlowBeanReloadProcessor taskFlowBeanReloadProcessor;

    public void modifyTaskFlow() {
        ClassPathResource classPathResource = new ClassPathResource("taskModify.json");
        try {
            String taskFlowJson = IOUtils.toString(classPathResource.getInputStream());
            WorkDefinition workDefinition = JSON.parseObject(taskFlowJson, WorkDefinition.class);
            taskFlowBeanReloadProcessor.reload(null, null, workDefinition);
            System.out.println("change");
        } catch (IOException e) {
            //
        }

    }
}
