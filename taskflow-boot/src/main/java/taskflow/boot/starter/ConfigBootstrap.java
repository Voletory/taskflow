package taskflow.boot.starter;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import taskflow.annotation.EnableTaskFlow;
import taskflow.config.bean.TaskflowConfiguration;
import taskflow.enums.ConfigSource;

import java.io.IOException;

/**
 * @author steven.zhu 2020/7/10 19:28.
 * @类描述：
 */
@Configuration
@EnableTaskFlow(source = ConfigSource.CUS)
public class ConfigBootstrap {

    @Bean
    public TaskflowConfiguration taskflowConfiguration() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("taskJson.json");
        String taskFlowJson = IOUtils.toString(classPathResource.getInputStream());
        TaskflowConfiguration taskflowConfiguration = JSON.parseObject(taskFlowJson, TaskflowConfiguration.class);
        return taskflowConfiguration;
    }
}
