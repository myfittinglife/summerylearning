package com.example.administrator.summarylearning.eventbus;

public class MessageEvent {     //自定义消息发送类，名字可随意起
    private String name;



    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }




    public MessageEvent(String name, String password) {
        this.name = name;
        this.tel = password;
    }


}
