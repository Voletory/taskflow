package taskflow.routing.match;

import taskflow.exception.TaskFlowException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author steven.zhu 2020/7/4 12:36.
 * @类描述：
 */
public class PatternFactory {

    // do not update map, after initialized
    private static Map<PatternType, PatternMatch> patternMatchMap = new HashMap<PatternType, PatternMatch>();

    static {
        for (PatternType patternType : PatternType.values()) {
            Class clazz = patternType.getClazz();
            try {
                Object o = clazz.newInstance();
                if (o instanceof PatternMatch) {
                    patternMatchMap.put(patternType, (PatternMatch) o);
                } else {
                    throw new TaskFlowException(o.getClass().getName() + " not instanceof PatternMatch");
                }
            } catch (InstantiationException e) {
                throw new TaskFlowException("PatternFactory init error,can not newInstance " + clazz.getName());
            } catch (IllegalAccessException e) {
                throw new TaskFlowException("PatternFactory init error,can not newInstance " + clazz.getName());
            }

            ;
        }
    }

    public static PatternMatch getPatternMatch(PatternType patternType) {
        PatternMatch ret = patternMatchMap.get(patternType);
        if (ret == null) {
            throw new TaskFlowException("not find " + patternType + "in PatternFactory!");
        }
        return ret;
    }

    public static void checkPattern(PatternType patternType) {
        if (!patternMatchMap.containsKey(patternType)) {
            throw new TaskFlowException("not find " + patternType + "in PatternFactory!");
        }
    }
}
