package taskflow.work.context;

/**
 * @author steven.zhu 2020/7/2 23:11.
 * @类描述：记录task记录
 */
public class TaskTrace {
    private String taskName;
    private String contextInfo;

    public TaskTrace(String taskName, String contextInfo) {
        this.taskName = taskName;
        this.contextInfo = contextInfo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getContextInfo() {
        return contextInfo;
    }

    public void setContextInfo(String contextInfo) {
        this.contextInfo = contextInfo;
    }
}
