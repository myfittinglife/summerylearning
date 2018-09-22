package com.example.administrator.summarylearning.startforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.btn_start1)
    Button btn_start1;
    @BindView(R.id.btn_start2)
    Button btn_start2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startforresult);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.btn_start1,R.id.btn_start2})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start1:
                startActivityForResult(new Intent(this,ActivityForResult1.class));
                break;
            case R.id.btn_start2:
                break;
        }

    }




}
