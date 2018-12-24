package com.example.administrator.summarylearning.littlefeature;

import android.content.Intent;
import android.net.Uri;
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
    @BindView(R.id.btn_evaluate)
    Button btnEvaluate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little_feature);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_judgeinstall, R.id.btn_judgenetstatus,R.id.btn_evaluate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_judgeinstall:         //判断是否安装某应用
                startActivity(new Intent(this, JudgeInstallActivity.class));
                break;
            case R.id.btn_judgenetstatus:      //判断网络状态
                startActivity(new Intent(this, JudgeNetstatusActivity.class));
                break;
            case R.id.btn_evaluate:             //打开应用商店对指定的包名应用进行评价
                String name = "com.example.asustp.coolweather";
                Uri uri = Uri.parse("market://details?id=" + name);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}
