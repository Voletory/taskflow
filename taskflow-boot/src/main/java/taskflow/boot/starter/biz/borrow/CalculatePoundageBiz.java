package taskflow.boot.starter.biz.borrow;

/**
 * @author steven.zhu 2020/7/10 13:30.
 * @类描述：
 */
public class CalculatePoundageBiz {
    /**
     * 按日计息
     */
    public void calculatorByDay() {
        System.out.println("按日计息");
    }

    /**
     * 等额本金
     */
    public void calculatorAverageCapital() {
        System.out.println("等额本金");
    }

    /**
     * 等额本息
     */
    public void calculatorAverageCapitalPlusInterest() {
        System.out.println("等额本息");
    }
}
