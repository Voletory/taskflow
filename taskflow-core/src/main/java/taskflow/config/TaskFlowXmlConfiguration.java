package taskflow.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author steven.zhu 2020/7/10 21:27.
 * @类描述：
 */
@Configuration
public class TaskFlowXmlConfiguration {

    @Bean
    public TaskFlowXmlBeanFactoryPostProcessor taskFlowXmlBeanFactoryPostProcessor() {
        return new TaskFlowXmlBeanFactoryPostProcessor();
    }


}
