package com.example.administrator.summarylearning.refreshandloadactivity.headlayout.apinet;

/**
 * @Author
 * @Time 2018/12/10 15:45
 * @Describe
 * @Modify
 */
public class RefreshData {
    boolean isOk;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String content;

    public RefreshData(boolean isOk, String content) {
        this.isOk = isOk;
        this.content = content;
    }

    public RefreshData(boolean isOk) {
        this.isOk = isOk;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

}
