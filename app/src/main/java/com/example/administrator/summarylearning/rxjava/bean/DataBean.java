package com.example.administrator.summarylearning.rxjava.bean;

/**
 * @Author
 * @Time    2018/11/30 10:24
 * @Describe
 * @Modify
 */
public class DataBean {
    @Override
    public String toString() {
        return "DataBean{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    private String name;
    private String url;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
