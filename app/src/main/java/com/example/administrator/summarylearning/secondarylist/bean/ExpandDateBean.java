package com.example.administrator.summarylearning.secondarylist.bean;

import java.util.List;

public class ExpandDateBean {
    private String fathername;
    private List<String> sonList;

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public List<String> getSonList() {
        return sonList;
    }

    public void setSonList(List<String> sonList) {
        this.sonList = sonList;
    }

}
