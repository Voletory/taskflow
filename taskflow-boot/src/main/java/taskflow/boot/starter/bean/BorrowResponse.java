package taskflow.boot.starter.bean;

import java.math.BigDecimal;

/**
 * @author steven.zhu 2020/7/10 19:11.
 * @类描述：
 */
public class BorrowResponse {
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
