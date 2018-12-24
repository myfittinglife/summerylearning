package com.example.administrator.summarylearning.retrofit;

/**
 * @Author
 * @Time 2018/11/23 10:48
 * @Describe
 * @Modify
 */
public class DataBean {

    private String title;           //标题
    private String author;          //作者
    private String Time;            //时间
    private String content;         //内容

    public DataBean() {
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", Time='" + Time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public DataBean(String title, String author, String time, String content) {
        this.title = title;
        this.author = author;
        Time = time;
        this.content = content;
    }


}
