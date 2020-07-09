package taskflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import taskflow.config.bean.TaskflowConfiguration;
import taskflow.config.bean.WorkDefinition;
import taskflow.constants.ConfigParams;

import java.util.Set;

/**
 * @author steven.zhu 2020/7/7 21:51.
 * @类描述：
 */
@Configuration
public class TaskFlowConfiguration {

    /**
     * 容器初始化之后再进行bean的注册
     * @param taskflowConfiguration
     * @param environment
     * @return
     */
    @Bean
    public TaskFlowBeanFactoryPostProcessor taskFlowBeanFactoryPostProcessor(TaskflowConfiguration taskflowConfiguration, ConfigurableEnvironment environment) {
        Boolean traceable = environment.getProperty(ConfigParams.WORK_TRACEABLE, Boolean.class, Boolean.FALSE);
        Set<WorkDefinition> workDefinitions=taskflowConfiguration.getWorkDefinitions();
        if (workDefinitions != null && workDefinitions.size() > 0) {
            for (WorkDefinition wd : workDefinitions) {
                if(wd.getTraceable()==null) {
                    wd.setTraceable(traceable);
                }
            }
        }
        Boolean ignoreNoExists = environment.getProperty(ConfigParams.WORK_NO_EXISTS_IGNORABLE, Boolean.class, Boolean.FALSE);
        return new TaskFlowBeanFactoryPostProcessor(taskflowConfiguration,ignoreNoExists);
    }

    @Bean
    public TaskFlowBeanReloadProcessor taskFlowBeanReloadProcessor(ConfigurableEnvironment environment) {
        Boolean reloadable = environment.getProperty(ConfigParams.RELOAD_ENABLE, Boolean.class, Boolean.TRUE);
        return new TaskFlowBeanReloadProcessor(reloadable);
    }

}
