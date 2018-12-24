package com.example.administrator.summarylearning.service;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.service.downservice.DownloadActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author      LD
 * @Time        2018/11/22
 * @Describe    服务的使用(启动服务别用action的方式启动,因为部分手机无法启动)
 * @Modify
 */
public class ServiceActivity extends AppCompatActivity {

    @BindView(R.id.btn_startservice)                //开启服务
            Button btnStartservice;
    @BindView(R.id.btn_stopservice)                 //停止服务
            Button btnStopservice;
    @BindView(R.id.btn_bindservice)                 //绑定服务
            Button btnBindservice;
    @BindView(R.id.btn_unbindservice)               //解绑服务
            Button btnUnbindservice;
    @BindView(R.id.btn_startintentservice)          //开启IntentService服务
            Button btnStartintentservice;
    @BindView(R.id.btn_download_activity)           //下载活动
    Button btnDownloadActivity;

    private MyService.DownloadBinder downloadBinder;

    private static final String TAG = "ceshi1126";

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            downloadBinder = (MyService.DownloadBinder) service;        //向下转型得到DownloadBinder实例，通过该方法调用Myservice中DownloadBinder中的任何public
            downloadBinder.startDownload();
            downloadBinder.getProgess();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_startservice, R.id.btn_stopservice, R.id.btn_bindservice, R.id.btn_unbindservice, R.id.btn_startintentservice, R.id.btn_download_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_startservice:                                                             //启动服务
                Intent startService = new Intent(this, MyService.class);
                startService(startService);
                break;
            case R.id.btn_stopservice:                                                              //停止服务
                Intent stopService = new Intent(this, MyService.class);
                stopService(stopService);
                break;
            case R.id.btn_bindservice:
                Intent bindIntent = new Intent(this, MyService.class);                              //绑定服务BIND_AUTO_CREATE表示在活动和服务进行绑定时自动创建服务，会使得onCreate()方法执行，但onStartCommand()方法不会执行
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbindservice:
                unbindService(connection);                                                          //解绑服务（解绑的时候服务也会销毁）
                break;
            case R.id.btn_startintentservice:                                                       //启动IntentService
                Log.i(TAG, "onViewClicked: 当前线程为：" + Thread.currentThread().getId());
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            case R.id.btn_download_activity:
                startActivity(new Intent(this, DownloadActivity.class));
                break;
            default:
                break;
        }
    }


}
