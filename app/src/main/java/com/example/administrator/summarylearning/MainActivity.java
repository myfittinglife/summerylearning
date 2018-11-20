package com.example.administrator.summarylearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.summarylearning.animation.AnimationActivity;
import com.example.administrator.summarylearning.eventbus.EventBusActivity;
import com.example.administrator.summarylearning.fragment.FragmentContainer;
import com.example.administrator.summarylearning.interestlablerecyclerview.ActivityInterestLable;
import com.example.administrator.summarylearning.interfacecallback.InterfaceCallbackActivity;
import com.example.administrator.summarylearning.jsbridge.JsBridgeActivity;
import com.example.administrator.summarylearning.littlefeature.LittleFeatureActivity;
import com.example.administrator.summarylearning.mixdevelopment.MixedDevelopActivity;
import com.example.administrator.summarylearning.mvpframework.MvpActivity;
import com.example.administrator.summarylearning.okgo.ActivityOkGo;
import com.example.administrator.summarylearning.recyclerview.RecyclerViewActivity;
import com.example.administrator.summarylearning.recyclerview.SlideRecyclerViewActivity;
import com.example.administrator.summarylearning.ringvibrateactivity.RingAndVibrateActivity;
import com.example.administrator.summarylearning.secondarylist.SecondaryListActivity;
import com.example.administrator.summarylearning.selectpicture.SelectPictureActivity;
import com.example.administrator.summarylearning.startforresult.StartActivity;
import com.example.administrator.summarylearning.systemsetting.SystemSettingActivity;
import com.example.administrator.summarylearning.text.TextActivity2;

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
    @BindView(R.id.btn_animation)
    Button btn_animation;
    @BindView(R.id.btn_vibrate_ring)     //震动
            Button btn_vibrate_ring;
    @BindView(R.id.btn_fragment)        //Fragment相关
            Button btn_fragment;
    @BindView(R.id.btn_jsbridge)
    Button btn_jsbridge;

    @BindView(R.id.btn_text)
    Button button;
    @BindView(R.id.btn_little_feature)      //小功能
            Button btnLittleFeature;
    @BindView(R.id.btn_mixed_development)   //混合开发
            Button btnMixedDevelopment;
    @BindView(R.id.btn_system_setting)      //系统设置
            Button btnSystemSetting;
    @BindView(R.id.btn_recyclerview)        //RecyclerView使用
    Button btnRecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_eventbus_activity, R.id.btn_start_for_result, R.id.btn_okgo_activity, R.id.btn_lable_activity, R.id.btn_matisse_activity, R.id.btn_callback, R.id.btn_get_phone_info, R.id.btn_expand_list
            , R.id.btn_animation, R.id.btn_vibrate_ring, R.id.btn_fragment, R.id.btn_jsbridge, R.id.btn_text, R.id.btn_little_feature, R.id.btn_mixed_development, R.id.btn_system_setting,R.id.btn_recyclerview
            ,R.id.btn_mvp})
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
            case R.id.btn_get_phone_info:                                                           //获取手机信息未实现
                Toast.makeText(this, "获取手机信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_expand_list:                                                              //二级列表
                startActivity(new Intent(this, SecondaryListActivity.class));
                break;
            case R.id.btn_animation:
                startActivity(new Intent(this, AnimationActivity.class));             //动画
                break;
            case R.id.btn_vibrate_ring:
                startActivity(new Intent(this, RingAndVibrateActivity.class));       //震动响铃
                break;
            case R.id.btn_fragment:                                                                 //Fragment相关
                startActivity(new Intent(this, FragmentContainer.class));
                break;
            case R.id.btn_jsbridge:                                                                 //JsBridge学习
                startActivity(new Intent(this, JsBridgeActivity.class));
                break;
            case R.id.btn_mixed_development:                                                        //混合开发
                startActivity(new Intent(this, MixedDevelopActivity.class));
                break;
            case R.id.btn_text:
                startActivity(new Intent(this, TextActivity2.class));
                break;
            case R.id.btn_little_feature:
                startActivity(new Intent(this, LittleFeatureActivity.class));
                break;
            case R.id.btn_system_setting:                                                           //系统设置
                startActivity(new Intent(this, SystemSettingActivity.class));
                break;
            case R.id.btn_recyclerview:
                startActivity(new Intent(this, RecyclerViewActivity.class));                        //RecyclerView使用
                break;
            case R.id.btn_mvp:                                                                      //MVP架构使用
                startActivity(new Intent(this, MvpActivity.class));
                break;
            default:
                break;
        }
    }
}
