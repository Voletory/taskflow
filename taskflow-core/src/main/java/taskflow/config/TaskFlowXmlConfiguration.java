package taskflow.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import taskflow.constants.ConfigParams;

/**
 * @author steven.zhu 2020/7/10 21:27.
 * @类描述：
 */
@Configuration
public class TaskFlowXmlConfiguration {

    private final String DEFAULT_TASK_FLOW_PATH = "taskflow.xml";

    @Bean
    public TaskFlowXmlBeanFactoryPostProcessor taskFlowXmlBeanFactoryPostProcessor(ConfigurableEnvironment environment) {
        String taskFlowPath = environment.getProperty(ConfigParams.TASKFLOW_PATH, String.class, DEFAULT_TASK_FLOW_PATH);
        return new TaskFlowXmlBeanFactoryPostProcessor(taskFlowPath);
    }

    @Bean
    public TaskFlowBeanReloadProcessor taskFlowBeanReloadProcessor(ConfigurableEnvironment environment) {
        Boolean reloadable = environment.getProperty(ConfigParams.RELOAD_ENABLE, Boolean.class, Boolean.TRUE);
        return new TaskFlowBeanReloadProcessor(reloadable);
    }
}
