package com.example.administrator.summarylearning.recyclerview.model;

/**
 * 作者    LD
 * 时间    2018/11/16 15:48
 * 描述    数据Bean
 */
public class BasicModel {

    String url;                             //图片地址
    String title;                           //标题
    String name;                            //姓名
    String favorites;                       //点赞
    String comments;                        //评论


    public BasicModel(String url, String title, String name, String favorites, String comments) {
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
