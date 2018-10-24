package com.example.administrator.summarylearning.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.fragment.fragmentlifecycle.LogEvent;
import com.example.administrator.summarylearning.fragment.fragmentlifecycle.OneFragment;
import com.example.administrator.summarylearning.fragment.fragmentlifecycle.TwoFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 盛放Fragment的容器
 * 本Activity学习内容：Fragment生命周期、Fragment的基本使用：添加、替换
 */
public class FragmentContainer extends AppCompatActivity {

    @BindView(R.id.btn_to_another_activity)   //跳转至其他功能节目
    Button btn_to_another_activity;
    @BindView(R.id.btn_add_fragment)         //添加碎片
    Button btnAddFragment;
    @BindView(R.id.btn_replace_fragmenttwo)  //替换为碎片一
    Button btnReplaceFragmenttwo;
    @BindView(R.id.btn_replace_fragmentone)  //替换为碎片二
    Button btnReplaceFragmentone;
    @BindView(R.id.tv_log)                  //存写日志
    TextView tvLog;
    @BindView(R.id.ll_container)            //防止碎片
    LinearLayout llContainer;
    @BindView(R.id.btn_clear_log)           //清空日志
    Button btnClearLog;

    private static final String TAG = "FragmentContainer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        tvLog.append(TAG+"："+"onCreate"+"\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvLog.append(TAG+"："+"onStart"+"\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvLog.append(TAG+"："+"onResume"+"\n");

    }

    @Override
    protected void onPause() {
        super.onPause();
        tvLog.append(TAG+"："+"onPause"+"\n");

    }

    @Override
    protected void onStop() {
        super.onStop();
        tvLog.append(TAG+"："+"onStop"+"\n");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tvLog.append(TAG+"："+"onRestart"+"\n");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        tvLog.append(TAG+"："+"onDestroy"+"\n");

    }

    @OnClick({R.id.btn_to_another_activity, R.id.btn_add_fragment, R.id.btn_replace_fragmenttwo, R.id.btn_replace_fragmentone,R.id.btn_clear_log})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_to_another_activity:
                Intent intent = new Intent(this, FunctionListActivity.class);        //跳转至功能集合学习界面
                startActivity(intent);
                break;
            case R.id.btn_add_fragment:                             //添加碎片一
                OneFragment oneFragment = new OneFragment();
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.add(R.id.ll_container, oneFragment);
                transaction1.commitAllowingStateLoss();              //提交与commit一样，但是会规避很多错
                break;
            case R.id.btn_replace_fragmenttwo:                       //由一替换为二
                TwoFragment twoFragment = new TwoFragment();
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
                transaction2.replace(R.id.ll_container, twoFragment);
                transaction2.commitAllowingStateLoss();
                break;
            case R.id.btn_replace_fragmentone:                       //由二替换为一
                OneFragment oneFragment3 = new OneFragment();
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                FragmentTransaction transaction3 = fragmentManager3.beginTransaction();
                transaction3.replace(R.id.ll_container, oneFragment3);
                transaction3.commitAllowingStateLoss();
                break;
            case R.id.btn_clear_log:                                   //清空日志
                tvLog.setText("");
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(LogEvent event) {
        tvLog.append(event.getLogStr());
    }
}
