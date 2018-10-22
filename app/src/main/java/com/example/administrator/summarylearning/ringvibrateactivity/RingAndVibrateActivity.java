package com.example.administrator.summarylearning.ringvibrateactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.ringvibrateactivity.ring.RingHelper;
import com.example.administrator.summarylearning.ringvibrateactivity.vibrate.VibrateHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RingAndVibrateActivity extends AppCompatActivity {

    @BindView(R.id.btn_sart_vibrate)
    Button btnSartVibrate;
    @BindView(R.id.btn_stop_vibrate)
    Button btnStopVibrate;
    @BindView(R.id.btn_start_ring)
    Button btnStartRing;
    @BindView(R.id.btn_stop_ring)
    Button btnStopRing;
    private boolean isVibrate = false;
    private boolean isRing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibratering);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_sart_vibrate, R.id.btn_stop_vibrate,R.id.btn_start_ring,R.id.btn_stop_ring})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sart_vibrate:     //震动
                VibrateHelper.VibrateHelper(RingAndVibrateActivity.this, new long[]{800, 1000, 800, 1000, 800, 1000}, true);
                isVibrate = true;
                break;
            case R.id.btn_stop_vibrate:     //关闭震动
                stopVibrate();
                break;
            case R.id.btn_start_ring:       //响铃
                RingHelper.playRing(RingAndVibrateActivity.this);
                isRing=true;
                break;
            case R.id.btn_stop_ring:        //关闭铃声
                stopRing();
                break;
        }
    }

    private void stopVibrate() {
        if (isVibrate) {
            isVibrate = false;
            VibrateHelper.CancleVibrate(RingAndVibrateActivity.this);
        }
    }
    private void stopRing(){
        if(isRing){
            isRing=false;
            RingHelper.stopRing();
        }
    }
}
