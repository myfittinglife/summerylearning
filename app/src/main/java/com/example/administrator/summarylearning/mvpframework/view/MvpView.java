package com.example.administrator.summarylearning.mvpframework.view;

/**
 * @Author
 * @Time            2018/11/19 17:22
 * @Describe        V层      供Activity和Fragment使用
 * @Modify
 */
public interface MvpView {
    /**
     * 更新UI操作
     * @param text  更新的内容
     */
    void updateTv(String text);
}
