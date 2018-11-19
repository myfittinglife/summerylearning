package com.example.administrator.summarylearning.mvpframework;

/**
 * @Author          LD
 * @Time            2018/11/19 17:40
 * @Describe        网络请求后的回调
 * @Modify
 */
public interface Callback {

    /**
     * 请求回调接口
     */
    void onResult(String text);
}
