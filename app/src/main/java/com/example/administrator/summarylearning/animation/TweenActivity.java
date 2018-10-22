package com.example.administrator.summarylearning.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
 * 补间动画：平移、缩放、旋转、透明度
 */
public class TweenActivity extends AppCompatActivity {

    @BindView(R.id.iv_animation)
    ImageView iv_animation;

    @BindView(R.id.btn_accelerate)
    Button btn_accelerate;
    @BindView(R.id.btn_decelerate)
    Button btnDecelerate;
    @BindView(R.id.btn_accelerate_decelerate)
    Button btnAccelerateDecelerate;


    @BindView(R.id.btn_anticipate)
    Button btnAnticipate;
    @BindView(R.id.btn_anticipate_overshoot)
    Button btnAnticipateOvershoot;
    @BindView(R.id.btn_bounce)
    Button btn_bounce;

    @BindView(R.id.btn_cycle)
    Button btnCycle;
    @BindView(R.id.btn_linear)
    Button btnLinear;
    @BindView(R.id.btn_overshoot)
    Button btnOvershoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_accelerate, R.id.btn_decelerate, R.id.btn_accelerate_decelerate, R.id.btn_anticipate, R.id.btn_anticipate_overshoot, R.id.btn_bounce, R.id.btn_cycle, R.id.btn_linear,R.id.btn_overshoot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_accelerate:                                                               //加速插值器
                Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.accelerate_animation);
                animation1.setAnimationListener(new Animation.AnimationListener() {     //设置监听器重复启动动画
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                            animation.reset();      //没这个光start只会执行两次
                            animation.start();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
//                animation1.setRepeatMode(Animation.REVERSE);              不成功
//                animation1.setRepeatCount(Animation.INFINITE);  //无限循环
                iv_animation.startAnimation(animation1);

                break;
            case R.id.btn_decelerate:
                Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_accelerate_decelerate:
                Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_anticipate:
                Toast.makeText(getApplicationContext(),"4",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_anticipate_overshoot:
                Toast.makeText(getApplicationContext(),"5",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_bounce:
                Toast.makeText(getApplicationContext(),"6",Toast.LENGTH_SHORT).show();

                //bounce插值器     动画结束的时候弹起，类似皮球落地，会弹几下才停止
                //跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce_tween_animation);
                iv_animation.startAnimation(animation);
                break;
            case R.id.btn_cycle:
                Toast.makeText(getApplicationContext(),"7",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_linear:
                Toast.makeText(getApplicationContext(),"8",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_overshoot:
                Toast.makeText(getApplicationContext(),"9",Toast.LENGTH_SHORT).show();

                break;
        }
    }




}
