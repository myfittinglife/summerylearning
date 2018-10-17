package com.example.administrator.summarylearning.animation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 动画
 */
public class AnimationActivity extends AppCompatActivity {


    @BindView(R.id.btn_start_frame_animation)
    Button btn_start_frame_animation;
    @BindView(R.id.btn_stop_frame_animation)
    Button btn_stop_frame_animation;
    @BindView(R.id.iv_frame_animation)
    ImageView iv_frame_animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

    }
    @OnClick({R.id.btn_start_frame_animation,R.id.btn_stop_frame_animation})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_start_frame_animation:                                                                  //开启帧动画
                iv_frame_animation.setBackgroundResource(R.drawable.frame_animation_list);
                AnimationDrawable animationDrawable= (AnimationDrawable) iv_frame_animation.getBackground();
                animationDrawable.start();
                break;
            case R.id.btn_stop_frame_animation:                                                                   //关闭帧动画
                AnimationDrawable stopAnimationDrawable= (AnimationDrawable) iv_frame_animation.getBackground();
                stopAnimationDrawable.stop();
                break;
        }
    }
}
