package com.example.administrator.summarylearning.littlefeature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.littlefeature.judgeinstall.JudgeInstallActivity;
import com.example.administrator.summarylearning.littlefeature.judgenetworkstatus.JudgeNetstatusActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者       LD
 * 时间       2018.11.5   17:40
 * 描述       小功能集合
 */
public class LittleFeatureActivity extends AppCompatActivity {

    @BindView(R.id.btn_judgeinstall)        //判断是否安装某应用
            Button btnJudgeinstall;
    @BindView(R.id.btn_judgenetstatus)
    Button btnJudgenetstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little_feature);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_judgeinstall, R.id.btn_judgenetstatus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_judgeinstall:     //判断是否安装某应用
                startActivity(new Intent(this, JudgeInstallActivity.class));
                break;
            case R.id.btn_judgenetstatus:      //判断网络状态
                startActivity(new Intent(this, JudgeNetstatusActivity.class));
            default:
                break;
        }
    }
}
