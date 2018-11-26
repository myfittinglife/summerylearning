package com.example.administrator.summarylearning.service.downservice;

/**
 * @Author      LD
 * @Time        2018/11/26 10:25
 * @Describe    回调接口，对下载过程中的各种状态进行监听和回调
 * @Modify
 */
public interface DownloadListener {

    //下载进度
    void onProgress(int progress);
    //下载成功
    void onSuccess();
    //下载失败
    void onFailed();
    //下载停止
    void onPause();
    //下载取消事件
    void onCanceled();


}
