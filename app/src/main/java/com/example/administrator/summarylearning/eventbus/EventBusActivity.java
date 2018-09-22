package com.example.administrator.summarylearning.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * EventBus的基本学习
 */
public class EventBusActivity extends AppCompatActivity {

    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.btn_sent_activity)
    Button btn_sentmessage;
    @BindView(R.id.btn_sent_thread)
    Button btn_sent_thread;
    @BindView(R.id.btn_cleantext)
    Button btn_cleantext;
    @BindView(R.id.btn_toactivity)
    Button btn_toActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);   //注册订阅者
    }

    @OnClick({R.id.btn_sent_activity, R.id.btn_sent_thread,R.id.btn_cleantext,R.id.btn_toactivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sent_activity:
                EventBus.getDefault().post(new MessageEvent("小栋", "152****3160"));      //1、主线程中发送
                break;
            case R.id.btn_sent_thread:                                                                    //2、子线程中发送
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        EventBus.getDefault().post(new MessageEvent("小李", "186****5391"));
                    }
                }).start();
                break;
            case R.id.btn_cleantext:
                tv_message.setText("");
                break;
            case R.id.btn_toactivity:
                startActivity(new Intent(this,AnotherActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 定义处理接受的方法
     * @param event     自定义的事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(MessageEvent event) {
        tv_message.setText("姓名：" + event.getName() +"  "+ "电话：" + event.getTel());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); //防止内存泄漏
    }
}
