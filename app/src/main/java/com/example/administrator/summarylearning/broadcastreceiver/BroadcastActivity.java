package com.example.administrator.summarylearning.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author LD
 * @Time 2018/11/28
 * @Describe 广播接收器
 * @Modify
 */
public class BroadcastActivity extends AppCompatActivity {

    @BindView(R.id.btn_register_network)
    Button btnRegisterNetwork;
    @BindView(R.id.btn_sendstandbroadcast)
    Button btnStandBroadcast;
    @BindView(R.id.btn_localbroadcast)      //本地广播
    Button btnLocalbroadcast;

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    //本地广播相关
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        ButterKnife.bind(this);


    }

    //注册网络状态变化监听器
    private void registerNetworkstatus() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");     //想要监听的广播
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    //发送标准广播
    private void sendStandBroadcast() {
        Intent intent = new Intent("com.example.broadcasttext.MY_BROADCAST");//自定义的名字，想要监听的广播
        sendBroadcast(intent);
    }

    //注册本地广播监听器
    private void registerLocalBroadcast(){
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();

        localBroadcastManager = LocalBroadcastManager.getInstance(this);    //获取实例
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);     //注册本地广播监听器
    }

    //发送本地广播
    private void sendLocalBroadcast(){
        Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
        localBroadcastManager.sendBroadcast(intent);    //发送本地广播
    }



    @OnClick({R.id.btn_register_network, R.id.btn_sendstandbroadcast,R.id.btn_localbroadcast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register_network:             //注册网络状态监听器
                registerNetworkstatus();                //（小问题，多次点击注册，都会弹出网络发生变化弹窗）
                break;
            case R.id.btn_sendstandbroadcast:           //发送标准广播
                sendStandBroadcast();
                break;
            case R.id.btn_localbroadcast:               //发送本地广播
                registerLocalBroadcast();
                sendLocalBroadcast();
                break;
            default:
                break;
        }
    }


    //网络状态变化的监听器
    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(), "网络发生改变", Toast.LENGTH_SHORT).show();

            //connectivitymanager系统服务类，专门用来管理网络连接
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "网络可用", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //本地广播监听器
    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"本地广播",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册的监听器都需要取消注册监听
        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver);
        }
        if(localReceiver!=null){
            localBroadcastManager.unregisterReceiver(localReceiver);
        }

    }
}
