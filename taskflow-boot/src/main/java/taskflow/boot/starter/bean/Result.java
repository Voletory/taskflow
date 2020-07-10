package taskflow.boot.starter.bean;

public class Result<T> extends BaseResult {
    private T data;

    public Result() {
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
