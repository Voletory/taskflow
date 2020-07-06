package taskflow.routing;

import org.apache.commons.lang3.builder.ToStringBuilder;
import taskflow.routing.match.PatternFactory;
import taskflow.routing.match.PatternMatch;
import taskflow.routing.match.PatternType;
import taskflow.work.context.WorkContext;

/**
 * @author steven.zhu 2020/7/4 12:25.
 * @类描述：
 */
public class PatternRoutingCondition extends AbstractRoutingCondition {
    private PatternType patternType;
    private String condition;

    @Override
    public boolean matched(WorkContext workContext) {
        String routingKey = workContext.getRoutingKey();
        PatternMatch patternMatch = PatternFactory.getPatternMatch(patternType);
        return patternMatch.isMatched(routingKey, condition);
    }

    public PatternType getPatternType() {
        return patternType;
    }

    public void setPatternType(PatternType patternType) {
        this.patternType = patternType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
