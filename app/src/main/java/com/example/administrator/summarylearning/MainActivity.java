package com.example.administrator.summarylearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.eventbus.EventBusActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_eventbus_activity)
    Button btn_eventbus_activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_eventbus_activity)
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_eventbus_activity:
                startActivity(new Intent(this, EventBusActivity.class));
        }
    }
}
