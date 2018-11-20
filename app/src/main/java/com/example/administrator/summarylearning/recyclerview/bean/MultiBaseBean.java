package com.example.administrator.summarylearning.recyclerview.bean;

/**
 * @Author      LD
 * @Time        2018/11/20 11:31
 * @Describe    多选bean类
 * @Modify
 */
public class MultiBaseBean {

    private String iconUrl;
    private String name;
    /**
     *是否被选择
     */
    private boolean isChoose;



    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }


    public MultiBaseBean(String iconUrl, String name) {
        this.iconUrl = iconUrl;
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
