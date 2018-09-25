package com.example.administrator.summarylearning.startforresult;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.btn_start1)
    Button btn_start1;
    @BindView(R.id.btn_start2)
    Button btn_start2;
    @BindView(R.id.tv_content1)
    TextView tv_content1;
    @BindView(R.id.tv_content2)
    TextView tv_content2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startforresult);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_start1, R.id.btn_start2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start1:
                startActivityForResult(new Intent(this, ActivityForResult1.class), 1);    //requestCode用来判断是开启的哪一个活动
                break;
            case R.id.btn_start2:
                startActivityForResult(new Intent(this, ActivityForResult2.class), 2);    //requestCode用来判断是开启的哪一个活动
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String textStr;
        switch (requestCode) {
            case 1:                                         //活动一
                if(resultCode==100){
                    textStr = data.getStringExtra("data");
                    tv_content1.setText(textStr);
                }
                else {
                    Toast.makeText(getApplicationContext(),"返回失败",Toast.LENGTH_SHORT).show();
                }

                break;
            case 2:                                        //活动二
                textStr = data.getStringExtra("data");
                tv_content2.setText(textStr);
                break;
            default:
                break;
        }
    }
}
