package com.example.administrator.summarylearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.summarylearning.eventbus.EventBusActivity;
import com.example.administrator.summarylearning.interestlablerecyclerview.ActivityInterestLable;
import com.example.administrator.summarylearning.interfacecallback.InterfaceCallbackActivity;
import com.example.administrator.summarylearning.okgo.ActivityOkGo;
import com.example.administrator.summarylearning.secondarylist.SecondaryListActivity;
import com.example.administrator.summarylearning.selectpicture.SelectPictureActivity;
import com.example.administrator.summarylearning.startforresult.StartActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_eventbus_activity)
    Button btn_eventbus_activity;
    @BindView(R.id.btn_start_for_result)
    Button btn_start_for_result;
    @BindView(R.id.btn_okgo_activity)
    Button btn_okgo_activity;
    @BindView(R.id.btn_lable_activity)
    Button btn_lable_activity;
    @BindView(R.id.btn_matisse_activity)
    Button btn_matisse_activity;
    @BindView(R.id.btn_callback)
    Button btn_callback;
    @BindView(R.id.btn_get_phone_info)
    Button btn_get_phone_info;
    @BindView(R.id.btn_expand_list)
    Button btn_expand_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_eventbus_activity, R.id.btn_start_for_result, R.id.btn_okgo_activity,R.id.btn_lable_activity,R.id.btn_matisse_activity,R.id.btn_callback,R.id.btn_get_phone_info,R.id.btn_expand_list})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_eventbus_activity:                                                        //eventbus
                startActivity(new Intent(this, EventBusActivity.class));
                break;
            case R.id.btn_start_for_result:                                                         //startforresult
                startActivity(new Intent(this, StartActivity.class));
                break;
            case R.id.btn_okgo_activity:                                                            //OkGo框架
                startActivity(new Intent(this, ActivityOkGo.class));
                break;
            case R.id.btn_lable_activity:                                                           //兴趣标签
                startActivity(new Intent(this, ActivityInterestLable.class));
                break;
            case R.id.btn_matisse_activity:                                                         //打开相册活动
                startActivity(new Intent(this, SelectPictureActivity.class));
                break;
            case R.id.btn_callback:                                                                 //接口回调
                startActivity(new Intent(this, InterfaceCallbackActivity.class));
                break;
            case R.id.btn_get_phone_info:                                                           //获取手机信息
                Toast.makeText(this,"获取手机信息",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_expand_list:
                startActivity(new Intent(this, SecondaryListActivity.class));
                break;

            default:
                break;
        }
    }
}
