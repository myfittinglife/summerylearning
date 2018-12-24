package com.example.administrator.summarylearning.commonviewpager;

/**
 * @Author
 * @Time 2018/12/8 9:28
 * @Describe    数据
 * @Modify
 */
public class DataEntry {
    String imageResId;
    String desc;

    public DataEntry(String imageResId, String desc) {
        this.imageResId = imageResId;
        this.desc = desc;
    }

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


}
