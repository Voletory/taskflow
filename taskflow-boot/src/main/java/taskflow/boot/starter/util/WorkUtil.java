package taskflow.boot.starter.util;

/**
 * @author steven.zhu 2020/7/10 19:17.
 * @类描述：
 */
public abstract class WorkUtil {
    /**
     * 获取workID
     * @param args
     * @return
     */
    public static String getWorkId(Object...args) {
        return "applyAverageCapitalWork";
    }
}
