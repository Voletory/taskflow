package taskflow.routing.match;

/**
 * @author steven.zhu 2020/7/4 12:26.
 * @类描述：
 */
public enum  PatternType {
    regex(RegexPatternMatch.class),
    string(StringPatternMatch.class);

    private Class clazz;

    PatternType(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
