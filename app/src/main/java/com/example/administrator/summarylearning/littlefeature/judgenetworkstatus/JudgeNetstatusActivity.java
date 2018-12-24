package com.example.administrator.summarylearning.littlefeature.judgenetworkstatus;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author      LD
 * @Time        2018/11/24
 * @Describe    判断网络状态及监听
 * @Modify
 */
public class JudgeNetstatusActivity extends AppCompatActivity {

    @BindView(R.id.tv_status)           //状态显示
    TextView tvStatus;
    @BindView(R.id.btn_judgestatus)     //判断
    Button btnJudgestatus;

    private static final String TAG = "1124ceshi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge_netstatus);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_judgestatus)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_judgestatus:          //判断状态
                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
                    Log.i(TAG, "onViewClicked: 6.0版本以上");
                    if(newfun(this)){
                        tvStatus.setText("网络已连接");
                    }else {
                        tvStatus.setText("网络未连接");
                    }
                }else {
                    boolean isConneted = isConnected(this);
                    if(isConneted){
                        tvStatus.setText(getNetworkStatus(this));
                    }else {
                        tvStatus.setText("网络未连接");
                    }
                }
                break;
        }
    }

    //判断网络是否链接
    public boolean isConnected(Context context){
        //connectivitymanager系统服务类，专门用来管理网络连接
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager==null){
            return false;
        }
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo==null||!networkInfo.isAvailable()){
            return false;
        }
        return true;
    }

    //判断网络的状态
    public String getNetworkStatus(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        switch (networkInfo.getType()){
            case ConnectivityManager.TYPE_WIFI:
                return "wifi";
            case ConnectivityManager.TYPE_MOBILE:
                return "流量";
                default:
                    return String.valueOf(networkInfo.getType());
        }

//        if(networkInfo.getType()== ConnectivityManager.TYPE_MOBILE){
//            return "Mobile";
//        }else if(networkInfo.getType()== ConnectivityManager.TYPE_WIFI){
//            return "Wifi";
//        }else {
//            return "我也不知道是什么状态";
//        }




//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            NetworkCapabilities networkCapabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
//            isNetUsable = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
//        }
//        return isNetUsable;


    }

    //新的方法  android6.0之后的新方法
    public boolean newfun(Context context){
        boolean isNetUsable=false;
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager!=null){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                NetworkCapabilities networkCapabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
                isNetUsable = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
                if(isNetUsable){
                    if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                        Log.i(TAG, "newfun: wifi连接");
                    }if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                        Log.i(TAG, "newfun: 流量连接");
                    }
                }
            }
        }
        return isNetUsable;


    }

}
