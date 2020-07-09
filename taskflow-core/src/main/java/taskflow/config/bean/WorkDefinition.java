package taskflow.config.bean;

import java.util.List;

/**
 * Work定义
 */
public class WorkDefinition {
	private String workId;
	private String start;
	private String finish;
	private int maxTasks;
	private Boolean traceable;
	private String workClazz;
	//支持同一Task执行多次
	private List<TaskRef> taskRefs;

	public WorkDefinition() {
		maxTasks = 0;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getFinish() {
		return finish;
	}
	public void setFinish(String finish) {
		this.finish = finish;
	}
	public int getMaxTasks() {
		return maxTasks;
	}
	public void setMaxTasks(int maxTasks) {
		this.maxTasks = maxTasks;
	}
	public Boolean getTraceable() {
		return traceable == null ? Boolean.FALSE : traceable;
	}
	public void setTraceable(Boolean traceable) {
		this.traceable = traceable;
	}
	public String getWorkClazz() {
		return workClazz;
	}
	public void setWorkClazz(String workClazz) {
		this.workClazz = workClazz;
	}
	public List<TaskRef> getTaskRefs() {
		return taskRefs;
	}
	public void setTaskRefs(List<TaskRef> taskRefs) {
		this.taskRefs = taskRefs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((workId == null) ? 0 : workId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkDefinition other = (WorkDefinition) obj;
		if (workId == null) {
			if (other.workId != null)
				return false;
		} else if (!workId.equals(other.workId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "WorkDefinition [workId=" + workId + ", start=" + start + ", finish=" + finish + ", maxTasks=" + maxTasks
				+ ", traceable=" + traceable + ", workClazz=" + workClazz + ", taskRefs=" + taskRefs + "]";
	}

	public static class TaskRef{
		private String taskId;
		private String extra;
		public String getTaskId() {
			return taskId;
		}
		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}
		public String getExtra() {
			return extra;
		}
		public void setExtra(String extra) {
			this.extra = extra;
		}
		@Override
		public String toString() {
			return "TaskRef [taskId=" + taskId + ", extra=" + extra + "]";
		}
	}
}