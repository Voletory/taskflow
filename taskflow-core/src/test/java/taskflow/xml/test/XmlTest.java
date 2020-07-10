package taskflow.xml.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import taskflow.work.Work;
import taskflow.work.WorkFactory;

/**
 * @author steven.zhu 2020/7/10 13:13.
 * @类描述：
 */
public class XmlTest {

    @Test
    public void xmlTest() {
        new ClassPathXmlApplicationContext("config/taskflow.xml");
        Work applyDayWork = WorkFactory.createWork("applyDayWork");
        Work applyAverageCapitalWork = WorkFactory.createWork("applyAverageCapitalWork");
        applyDayWork.run();
        applyAverageCapitalWork.run();
    }
}
