package taskflow.boot.starter.biz.borrow;

import taskflow.boot.starter.bean.BorrowRequest;
import taskflow.work.Work;

import java.math.BigDecimal;

/**
 * @author steven.zhu 2020/7/10 13:29.
 * @类描述：
 */
public class BorrowBiz {

    public void borrow(Work work, BorrowRequest request) {
        BigDecimal amount = request.getAmount();
        work.getWorkContext().put("amount", amount);
        System.out.println("生成借款单" + amount);
    }
}
