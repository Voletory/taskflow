package taskflow.boot.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import taskflow.annotation.EnableTaskFlow;
import taskflow.config.bean.TaskflowConfiguration;
import taskflow.enums.ConfigSource;

/**
 * @author steven.zhu 2020/7/10 19:28.
 * @类描述：
 */
@Configuration
@EnableTaskFlow(source = ConfigSource.XML)
public class ConfigBootstrap {

    @Bean
    public TaskflowConfiguration taskflowConfiguration() {
        TaskflowConfiguration taskflowConfiguration = new TaskflowConfiguration();
        return taskflowConfiguration;
    }
}
