package com.example.administrator.summarylearning.mvpframework.model;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.summarylearning.mvpframework.Callback;

/**
*  @Author      LD
*  @Time
*  @Describe    数据请求类,耗时操作等
*  @Modify      
*/
public class HttpModel {

    /**
     * 因网络异步请求，将获取到的数据通过接口传过去
     */
    private Callback callback;



    public HttpModel(Callback callback) {
        this.callback = callback;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    callback.onResult((String) msg.obj);
                    Log.i("1119ceshi", "handleMessage: 执行了网络请求任务");
                    break;
            }
        }
    };

    /**
     * 获取网络数据具体实现方法
     */
    public void requestMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message msg = handler.obtainMessage();
                    msg.what = 100;
                    msg.obj = "从网络获取到的数据";
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 取消Handler任务
     */
    public void revokeMessage(){
        //有问题，并未撤销handler任务
        handler.removeMessages(100);
        Log.i("1119ceshi", "revokeMessage: 取消handler任务");
    }



}
