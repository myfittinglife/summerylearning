package com.example.administrator.summarylearning.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.fragment.backstack_learn.BackStackActivity;
import com.example.administrator.summarylearning.fragment.createfragment.CreateFragment;
import com.example.administrator.summarylearning.fragment.createfragment.CreateFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment路线的学习
 * 包含内容：
 * 1、Fragment的回退栈
 * 2、Fragment和Activity之间的通信
 * 3、Fragment和Activity之间的通信的优化
 * 4、Fragmentation框架学习
 * 5、如何处理运行时配置发生变化（以屏幕翻转为例）
 * 6、使用Arguments来创建Fragment,通过Bundle方式传参（上班族的程序员看过来）
 */
public class FunctionListActivity extends AppCompatActivity {

    @BindView(R.id.btn_backstack)           //1、回退栈学习
            Button btnBackstack;
    @BindView(R.id.btn_communication)
    Button btnCommunication;
    @BindView(R.id.btn_create_fragment)
    Button btnCreateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_list);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btn_backstack, R.id.btn_communication,R.id.btn_create_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_backstack:            //1、回退栈
                Intent intent1 = new Intent(this, BackStackActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_communication:         //2、fragment和activity的通信
                break;
            case R.id.btn_create_fragment:       //6、Arguments方式创建Fragment
                Intent intent6 = new Intent(this, CreateFragmentActivity.class);
                startActivity(intent6);
                break;
            default:
                break;
        }

    }
}
