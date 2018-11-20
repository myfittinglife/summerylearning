package com.example.administrator.summarylearning.recyclerview.bean;


/**
*  @Author      LD
*  @Time        2018.11.16
*  @Describe    数据Bean
*  @Modify
*/
public class BasicBean {

    /**
     * 图片地址
     */
    String url;
    /**
     * 标题
     */
    String title;
    /**
     * 姓名
     */
    String name;
    /**
     * 点赞
     */
    String favorites;
    /**
     * 评论
     */
    String comments;


    public BasicBean(String url, String title, String name, String favorites, String comments) {
        this.url = url;
        this.title = title;
        this.name = name;
        this.favorites = favorites;
        this.comments = comments;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


}
