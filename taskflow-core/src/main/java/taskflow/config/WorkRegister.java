package taskflow.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.util.Assert;
import taskflow.config.bean.WorkDefinition;
import taskflow.config.register.RegisterLogger;
import taskflow.constants.WorkPropName;
import taskflow.enums.TFLogType;
import taskflow.work.DefaultConditionRouteWork;
import taskflow.work.SequentialWork;
import taskflow.work.Work;
import taskflow.work.WorkFactory;

import java.util.List;

/**
 * @author steven.zhu 2020/7/6 19:40.
 * @类描述： work注册
 */
public interface WorkRegister extends ConfigSourceAware {

    default BeanDefinition registerWork(BeanDefinitionRegistry registry, WorkDefinition workDefinition) {
        Class<?> workClazz = null;
        try {
            workClazz = Class.forName(workDefinition.getWorkClazz());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Assert.isTrue(Work.class.isAssignableFrom(workClazz), "Work '" + workDefinition.getWorkId() + "' must has a class of type of Work");
        RootBeanDefinition work = new RootBeanDefinition();
        work.setBeanClass(workClazz);
        // 设置为原型模式，spring每次获取都重新生成
        work.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE);
        work.getPropertyValues().add(WorkPropName.NAME, workDefinition.getWorkId());
        work.getPropertyValues().add(WorkPropName.TRACEABLE, workDefinition.getTraceable());

        // 判断是否是条件路由work
        if (DefaultConditionRouteWork.class.isAssignableFrom(work.getBeanClass())) {//只有DefaultConditionRouteWork才解析start和finish
            String start = workDefinition.getStart();
            Assert.isTrue(!StringUtils.isEmpty(start), "Work '" + workDefinition.getWorkId() + "' must has a start task");
            String finish = workDefinition.getFinish();

            // 运行时bean
            RuntimeBeanReference startBean = new RuntimeBeanReference(start);
            work.getPropertyValues().add(WorkPropName.START, startBean);
            // 如果finish存在
            if (!StringUtils.isEmpty(finish)) {
                RuntimeBeanReference finishBean = new RuntimeBeanReference(finish);
                work.getPropertyValues().add(WorkPropName.FINISH, finishBean);
            }
        } else if (SequentialWork.class.isAssignableFrom(work.getBeanClass())) {//只有SerialRouteWork才解析sequence
            // 创建map引用，运行时候进行解析
            ManagedMap<String, RuntimeBeanReference> tasksMap = new ManagedMap<>();
            List<WorkDefinition.TaskRef> taskRefs = workDefinition.getTaskRefs();
            Assert.isTrue(taskRefs != null && taskRefs.size() > 0, "the Work '" + workDefinition.getWorkId() + "' must has a task-ref at least!");
//            Map<String, String> taskRefExtraMap = new HashMap<>();
            // 遍历封装map
            for (WorkDefinition.TaskRef taskRef : taskRefs) {
                if (StringUtils.isEmpty(taskRef.getTaskId())) {
                    throw new NullPointerException("the Work '" + workDefinition.getWorkId() + "' has a empty task-ref");
                }
                tasksMap.put(taskRef.getTaskId(), new RuntimeBeanReference(taskRef.getTaskId()));
//                if (!StringUtils.isBlank(taskRef.getExtra())) {
//                    taskRefExtraMap.put(taskRef.getTaskId(), taskRef.getExtra());
//                }
            }
            work.getPropertyValues().add(WorkPropName.TASKS, tasksMap);
//            if (taskRefExtraMap.size() > 0) {
//                ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
//                constructorArgumentValues.addGenericArgumentValue(taskRefExtraMap);
//                work.setConstructorArgumentValues(constructorArgumentValues);
//            }
        }
        work.getPropertyValues().add(WorkPropName.MAX_TASKS, Integer.valueOf(workDefinition.getMaxTasks()));
        if (registry.containsBeanDefinition(workDefinition.getWorkId()))
            registry.removeBeanDefinition(workDefinition.getWorkId());
        BeanDefinitionReaderUtils.registerBeanDefinition(new BeanDefinitionHolder(work, workDefinition.getWorkId()), registry);

        RegisterLogger.log(getConfigSource(), TFLogType.WORK, workDefinition.getWorkId(), workDefinition.toString());
        return work;
    }

    default BeanDefinition registerWorkFactory(BeanDefinitionRegistry registry) {
        String beanName = WorkFactory.class.getSimpleName();
        if (registry.containsBeanDefinition(beanName))
            return registry.getBeanDefinition(beanName);
        RootBeanDefinition workerFactory = new RootBeanDefinition();
        workerFactory.setBeanClass(WorkFactory.class);
        BeanDefinitionReaderUtils.registerBeanDefinition(new BeanDefinitionHolder(workerFactory, beanName), registry);

        RegisterLogger.log(getConfigSource(), TFLogType.WORK_FACTORY,beanName, "[beanId:"+beanName+" , class:"+WorkFactory.class.getName()+"]");
        return workerFactory;
    }
}
