package taskflow.enums;

/**
 * xsd自定义的标签
 * 
 */
public enum Tag {
	WORK_FACTORY("workFactory"),
	WORK("work"),
	TASK("task"),
	ROUTING("routing");
	public String VALUE;
	private Tag(String VALUE) {
		this.VALUE = VALUE;
	}

	public String getTagName() {
		return "tf:" + VALUE;
	}
}
