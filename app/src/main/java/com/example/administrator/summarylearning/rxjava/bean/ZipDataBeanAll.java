package com.example.administrator.summarylearning.rxjava.bean;

/**
 * @Author
 * @Time 2018/12/1 11:24
 * @Describe
 * @Modify
 */
public class ZipDataBeanAll {


    private  ZipDataBean1 zipDataBean1;
    private  ZipDataBean2 zipDataBean2;

    public ZipDataBeanAll(ZipDataBean1 zipDataBean1, ZipDataBean2 zipDataBean2) {
        this.zipDataBean1 = zipDataBean1;
        this.zipDataBean2 = zipDataBean2;
    }
    public String show(){
        return zipDataBean1.getName()+zipDataBean2.getAge()+"\n"+zipDataBean1.getUrl()+zipDataBean2.getUrl();
    }


}
