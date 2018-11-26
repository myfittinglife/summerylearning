package com.example.administrator.summarylearning.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * @Author          LD
 * @Time            2018/11/26 9:57
 * @Describe        异步的会自动停止的服务，该服务会在执行完成后自动停止！！！
 * @Modify
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "ceshi1126";


    //必须写这个无参的构造函数，不然在AndroidManifest中注册时会显示has no default constructor
    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {    //该方法已经在子线程中了
        //打印当前线程的id
        Log.i(TAG, "onHandleIntent: "+"Thread id is"+Thread.currentThread().getId());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: 线程执行完毕，onDestroy（）函数调用");
    }
}
