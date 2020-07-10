package taskflow.boot.starter.bean;

import java.math.BigDecimal;

/**
 * @author steven.zhu 2020/7/10 19:25.
 * @类描述：
 */
public class BorrowRequest {
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
