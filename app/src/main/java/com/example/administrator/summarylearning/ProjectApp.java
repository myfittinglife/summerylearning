package com.example.administrator.summarylearning;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class ProjectApp extends Application {

    //activity集合
    private List<Activity> activities;
    private static ProjectApp instance;

    private boolean isDebug = false;
    private RefWatcher refWatcher;


    @Override
    public void onCreate() {
        super.onCreate();
        initOKGO();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initChannel();
        }
        activities = new ArrayList<>();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        instance = this;
        refWatcher = LeakCanary.install(this);//内存泄漏,全局调用


    }

    public static synchronized ProjectApp getInstance() {
        return instance;
    }

    private void initOKGO() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志

        //超时时间设置，默认60秒
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);   //全局的连接超时时间
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));  ////使用sp保持cookie，如果cookie不过期，则一直有效

        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders();                           //可添加公共头部分

    }

    //通知渠道的建立
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initChannel() {
        String channelId = "download";                                                              //渠道ID
        String channelName = "下载消息";                                                            //渠道名称
        int importance = NotificationManager.IMPORTANCE_LOW;                                        //若为IMPORTANCE_HIGH会导致一直震动和响铃
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(true);      //允许该通知渠道下的通知显示角标
        channel.enableVibration(false);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

    }

    //管理Activity的生命周期
    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            activities.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            activities.remove(activity);
        }
    };

    //退出应用程序
    public void exit() {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i) != null) {
                activities.get(i).finish();
            }
        }
    }

    public  boolean isDebug() {
        try {
            ApplicationInfo info = getInstance().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    //用于监控Fragment的内存泄漏状况，看CreateFragment的使用
    public static RefWatcher getRefWatcher(Context context){
        ProjectApp projectApp = (ProjectApp) context.getApplicationContext();
        return projectApp.refWatcher;
    }


}
