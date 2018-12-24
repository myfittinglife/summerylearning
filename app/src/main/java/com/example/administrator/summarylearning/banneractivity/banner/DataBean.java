package com.example.administrator.summarylearning.banneractivity.banner;

/**
 * @Author
 * @Time 2018/12/8 14:23
 * @Describe
 * @Modify
 */
public class DataBean {

    String imageResId;
    String desc;

    public String getImageResId() {
        return imageResId;
    }

    public void setImageResId(String imageResId) {
        this.imageResId = imageResId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public DataBean(String imageResId, String desc) {
        this.imageResId = imageResId;
        this.desc = desc;
    }


}
