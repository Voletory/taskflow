package taskflow.boot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steven.zhu 2020/7/10 18:31.
 * @类描述：
 */
@SpringBootApplication
@RestController("/")
public class TaskFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskFlowApplication.class);
    }

    @RequestMapping("/")
    public String index() {
        return "TASKFLOW";
    }

}
