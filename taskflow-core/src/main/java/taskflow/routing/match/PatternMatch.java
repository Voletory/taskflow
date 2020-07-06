package taskflow.routing.match;

/**
 * @author steven.zhu 2020/7/4 12:31.
 * @类描述：
 */
public interface PatternMatch {
    boolean isMatched(String pattern, String value);
}
