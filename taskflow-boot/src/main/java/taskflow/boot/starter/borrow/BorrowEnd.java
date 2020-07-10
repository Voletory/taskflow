package taskflow.boot.starter.borrow;

import taskflow.boot.starter.bean.BorrowResponse;
import taskflow.work.Work;

import java.math.BigDecimal;

/**
 * @author steven.zhu 2020/7/10 13:32.
 * @类描述：
 */
public class BorrowEnd {

    public void endTask(Work work, BigDecimal amount) {
        BorrowResponse borrowResponse = new BorrowResponse();
        borrowResponse.setAmount(amount);
        work.getWorkContext().setResult(borrowResponse);
        System.out.println("借款结束");
    }
}
