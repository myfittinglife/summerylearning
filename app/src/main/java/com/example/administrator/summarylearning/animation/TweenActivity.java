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

    @OnClick({R.id.btn_accelerate, R.id.btn_decelerate, R.id.btn_accelerate_decelerate, R.id.btn_anticipate, R.id.btn_anticipate_overshoot, R.id.btn_bounce, R.id.btn_cycle, R.id.btn_linear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_accelerate:
                Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_decelerate:
                Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_accelerate_decelerate:
                Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_anticipate:
                Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_anticipate_overshoot:
                Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_bounce:
                //bounce插值器     动画结束的时候弹起，类似皮球落地，会弹几下才停止
                //跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce_tween_animation);
                iv_animation.startAnimation(animation);
                break;
            case R.id.btn_cycle:
                Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_linear:
                Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();

                break;
        }
    }




}
