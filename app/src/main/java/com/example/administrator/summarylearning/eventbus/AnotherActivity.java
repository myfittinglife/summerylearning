package com.example.administrator.summarylearning.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnotherActivity extends AppCompatActivity {

    @BindView(R.id.btn_sendmessage)
    Button btn_sendmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_another);
        ButterKnife.bind(this);


    }
    @OnClick({R.id.btn_sendmessage})
    public void onClick(View view){
        EventBus.getDefault().post(new MessageEvent("其他活动", "152****3160"));      //3、其他活动中发送   注意：此处不需要再次进行注册和解绑，否则报错
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
