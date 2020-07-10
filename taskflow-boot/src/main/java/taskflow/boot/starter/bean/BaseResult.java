package taskflow.boot.starter.bean;

public class BaseResult {
    private String code;
    private String msg;

    public BaseResult() {
        this.code = "0";
        this.msg = "success";
    }



    public BaseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean ifSuccessful() {
        return this.code.equals("0");
    }

}