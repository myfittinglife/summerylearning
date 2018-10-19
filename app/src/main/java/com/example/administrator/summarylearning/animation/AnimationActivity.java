package com.example.administrator.summarylearning.animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
    @BindView(R.id.btn_add_frame_animation)
    Button btn_add_frame_animation;
    @BindView(R.id.iv_frame_animation)
    ImageView iv_frame_animation;

//    @BindView(R.id.iv_tween_pic)        //补间动画
//            ImageView iv_tween_pic;
    @BindView(R.id.btn_start_tween)
    Button btn_start_tween;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_start_frame_animation, R.id.btn_stop_frame_animation, R.id.btn_add_frame_animation, R.id.btn_start_tween})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_frame_animation:                                                                  //开启帧动画
                iv_frame_animation.setBackgroundResource(R.drawable.frame_animation_list);
                AnimationDrawable animationDrawable = (AnimationDrawable) iv_frame_animation.getBackground();      //通过animation方式添加帧动画
                animationDrawable.start();
                break;
            case R.id.btn_stop_frame_animation:                                                                   //关闭帧动画
                AnimationDrawable stopAnimationDrawable = (AnimationDrawable) iv_frame_animation.getBackground();
                if (stopAnimationDrawable != null) {
                    stopAnimationDrawable.stop();
                } else {
                    Toast.makeText(getApplicationContext(), "没有帧动画", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_add_frame_animation:
                addFrame();
                break;
            case R.id.btn_start_tween:
                Intent intent = new Intent(this, TweenActivity.class);
                startActivity(intent);


                break;
            default:
                break;

        }
    }

    //通过代码方式添加帧动画
    public void addFrame() {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        int[] mipmaps = new int[]{R.drawable.run1, R.drawable.run2, R.drawable.run3, R.drawable.run4, R.drawable.run5, R.drawable.run6, R.drawable.run7};
        for (int i = 0; i < mipmaps.length; i++) {
            Drawable drawable = getResources().getDrawable(mipmaps[i]);
            animationDrawable.addFrame(drawable, 200);
        }
        animationDrawable.setOneShot(false);            //循环播放
        iv_frame_animation.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();
    }


}
