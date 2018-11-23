package com.example.administrator.summarylearning.retrofit;

/**
 * @Author
 * @Time 2018/11/23 16:45
 * @Describe        提交返回的bean
 * @Modify
 */
public class StatusBean {

    private String status;      //状态码
    private String isOk;        //true
    private String message;     //内容

    @Override
    public String toString() {
        return "StatusBean{" +
                "status='" + status + '\'' +
                ", isOk='" + isOk + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsOk() {
        return isOk;
    }

    public void setIsOk(String isOk) {
        this.isOk = isOk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
