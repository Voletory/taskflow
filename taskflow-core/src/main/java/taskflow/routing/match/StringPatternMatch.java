package taskflow.routing.match;

/**
 * @author steven.zhu 2020/7/4 12:33.
 * @类描述：
 */
public class StringPatternMatch implements PatternMatch {
    @Override
    public boolean isMatched(String pattern, String value) {
        return pattern == null ? value == null : pattern.equals(value);
    }
}
