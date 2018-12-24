package com.example.administrator.summarylearning.systemsetting;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者    LD
 * 修改
 * 时间    11.15 18:29
 * 描述    系统设置
 */
public class SystemSettingActivity extends AppCompatActivity {

    @BindView(R.id.btn_location)
    Button btnLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_location)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_location:
                //*跳转到系统设置来开启位置服务
                Intent intent =  new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                break;
        }
    }
}
