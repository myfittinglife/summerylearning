package com.example.administrator.summarylearning.fragment.fragmentlifecycle;

/**
 * 用于EventBus传输生命周期日志用
 */
public class LogEvent {

    public LogEvent(String logStr) {
        this.logStr = logStr;
    }

    private String logStr;

    public String getLogStr() {
        return logStr;
    }

    public void setLogStr(String logStr) {
        this.logStr = logStr;
    }

}
