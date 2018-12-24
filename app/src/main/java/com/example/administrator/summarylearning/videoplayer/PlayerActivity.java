package com.example.administrator.summarylearning.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author      LD
 * @Time        2018/12/14
 * @Describe    进入视频播放，多个按钮列表
 * @Modify
 */
public class PlayerActivity extends AppCompatActivity {

    @BindView(R.id.btn_goto_video)
    Button btnGotoVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_goto_video)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_goto_video:
                startActivity(new Intent(this,VideoPlayerActivity.class));
                break;
            default:
                break;
        }
    }
}
