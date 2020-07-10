package taskflow.boot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import taskflow.boot.starter.bean.BorrowRequest;
import taskflow.boot.starter.bean.BorrowResponse;
import taskflow.boot.starter.bean.Result;
import taskflow.boot.starter.util.WorkUtil;
import taskflow.work.Work;
import taskflow.work.WorkFactory;

/**
 * @author steven.zhu 2020/7/10 18:31.
 * @类描述：
 */
@SpringBootApplication
@RestController
public class TaskFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskFlowApplication.class);
    }

    @GetMapping("/borrow")
    public Result<BorrowResponse> borrow(BorrowRequest borrowRequest) {
        Work work = WorkFactory.createWork(WorkUtil.getWorkId());
        work.getWorkContext().put("request",borrowRequest);
        BorrowResponse result = work.run().getResult();
        return new Result<>(result);
    }
}
