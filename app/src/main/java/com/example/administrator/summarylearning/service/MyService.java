package com.example.administrator.summarylearning.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;

import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.administrator.summarylearning.R;

/**
*  @Author      LD
*  @Time        2018/11/22
*  @Describe    该方法用于测试服务的一些基本用法
*  @Modify
*/
public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.i(TAG, "startDownload: 开始下载");
        }
        public int getProgess(){
            Log.i(TAG, "getProgess: 获取进度");
            return 0;
        }
    }



    private static final String TAG = "MyService1124ceshi";

    public MyService() {
    }

    /**
     * 服务创建时调用（只会在服务第一次创建的时候调用）
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: 服务创建");


        //前台服务创建
        Intent intent = new Intent(this, ServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.item_pic)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.item_pic2))
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1,notification);          //让MyService变成一个前台服务(通知的id，Notification对象)
        //----------------------------------------------------------------------------------------------------------------------









    }

    /**
     * 每次服务启动的时候调用（用于服务一启动就立刻执行某个动作）每次启动都会被调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: 服务启动");

        //以下这种服务一旦启动之后，就会一直处于运行状态，必须调用stopService（）或者stopself()方法才能让服务停止下来
        new Thread(new Runnable() {
            @Override
            public void run() {
                //此处处理耗时的操作
                stopSelf();
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务销毁时调用（在该方法中回收不再使用的资源）
     */
    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: 服务销毁");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: 服务绑定");
        return mBinder;

    }
}
