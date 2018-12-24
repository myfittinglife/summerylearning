package com.example.administrator.summarylearning.rxjava.bean;

/**
 * @Author
 * @Time 2018/11/30 17:03
 * @Describe
 * @Modify
 */
public class TheDataBean2 {
    @Override
    public String toString() {
        return "TheDataBean{" +
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
