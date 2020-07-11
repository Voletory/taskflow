package taskflow.test.work;

import org.junit.Test;
import taskflow.routing.DefaultRouting;
import taskflow.routing.PatternRoutingCondition;
import taskflow.routing.RoutingCondition;
import taskflow.routing.match.PatternType;
import taskflow.task.ReflectedTaskRoutingWrap;
import taskflow.task.TaskMethodInvoker;
import taskflow.task.TaskRoutingWrap;
import taskflow.test.work.task.*;
import taskflow.work.DefaultRouteWork;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steven.zhu 2020/7/11 10:58.
 * @类描述：
 */
public class TestWork {

    @Test
    public void test() {
        TaskRoutingWrap taskRoutingWrap = new ReflectedTaskRoutingWrap();
        DefaultRouteWork work  = new DefaultRouteWork();
        work.setName("start");
        work.setStart(getStart());
        work.setFinish(getEnd());
        work.setRoutingKey("1");
        work.setMaxTasks(100);
        work.run();
    }

    public static TaskRoutingWrap getStart() {
        ReflectedTaskRoutingWrap reflectedTaskRoutingWrap = new ReflectedTaskRoutingWrap();
        TaskMethodInvoker taskMethodInvoker = new TaskMethodInvoker(new OneStation(), "pass");
        reflectedTaskRoutingWrap.setTaskMethodInvoker(taskMethodInvoker);
        DefaultRouting routing = new DefaultRouting();
        List<RoutingCondition> routingConditions = new ArrayList();
        PatternRoutingCondition patternRoutingCondition = new PatternRoutingCondition();
        patternRoutingCondition.setCondition("1");
        patternRoutingCondition.setPatternType(PatternType.string);
        patternRoutingCondition.setTaskRoutingWrap(getSecond());
        routingConditions.add(patternRoutingCondition);
        routing.setRoutingConditions(routingConditions);
        reflectedTaskRoutingWrap.setRouting(routing);
        return reflectedTaskRoutingWrap;
    }

    private static TaskRoutingWrap getSecond() {
        ReflectedTaskRoutingWrap reflectedTaskRoutingWrap = new ReflectedTaskRoutingWrap();
        TaskMethodInvoker taskMethodInvoker = new TaskMethodInvoker(new TwoStation(), "pass");
        reflectedTaskRoutingWrap.setTaskMethodInvoker(taskMethodInvoker);
        DefaultRouting routing = new DefaultRouting();
        List<RoutingCondition> routingConditions = new ArrayList();
        PatternRoutingCondition patternRoutingCondition = new PatternRoutingCondition();
        patternRoutingCondition.setCondition("1");
        patternRoutingCondition.setPatternType(PatternType.string);
        patternRoutingCondition.setTaskRoutingWrap(getThree());
        routingConditions.add(patternRoutingCondition);
        routing.setRoutingConditions(routingConditions);
        reflectedTaskRoutingWrap.setRouting(routing);
        return reflectedTaskRoutingWrap;
    }

    private static TaskRoutingWrap getThree() {
        ReflectedTaskRoutingWrap reflectedTaskRoutingWrap = new ReflectedTaskRoutingWrap();
        TaskMethodInvoker taskMethodInvoker = new TaskMethodInvoker(new ThreeStation(), "pass");
        reflectedTaskRoutingWrap.setTaskMethodInvoker(taskMethodInvoker);
        DefaultRouting routing = new DefaultRouting();
        List<RoutingCondition> routingConditions = new ArrayList();
        PatternRoutingCondition patternRoutingCondition = new PatternRoutingCondition();
        patternRoutingCondition.setCondition("1");
        patternRoutingCondition.setPatternType(PatternType.string);
        patternRoutingCondition.setTaskRoutingWrap(getFour());
        routingConditions.add(patternRoutingCondition);
        routing.setRoutingConditions(routingConditions);
        reflectedTaskRoutingWrap.setRouting(routing);
        return reflectedTaskRoutingWrap;
    }

    private static TaskRoutingWrap getFour() {
        ReflectedTaskRoutingWrap reflectedTaskRoutingWrap = new ReflectedTaskRoutingWrap();
        TaskMethodInvoker taskMethodInvoker = new TaskMethodInvoker(new FourStation(), "pass");
        reflectedTaskRoutingWrap.setTaskMethodInvoker(taskMethodInvoker);
        return reflectedTaskRoutingWrap;
    }

    private static TaskRoutingWrap getEnd() {
        ReflectedTaskRoutingWrap reflectedTaskRoutingWrap = new ReflectedTaskRoutingWrap();
        TaskMethodInvoker taskMethodInvoker = new TaskMethodInvoker(new EndStation(), "pass");
        reflectedTaskRoutingWrap.setTaskMethodInvoker(taskMethodInvoker);
        return reflectedTaskRoutingWrap;
    }
}
