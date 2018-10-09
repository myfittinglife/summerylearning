package com.example.administrator.summarylearning.startforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityForResult1 extends AppCompatActivity {

    @BindView(R.id.btn_back1)
    Button btn_back1;
    @BindView(R.id.btn_back2)
    Button btn_back2;
    @BindView(R.id.et_text1)
    EditText et_text1;
    @BindView(R.id.et_text2)
    EditText et_text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_back1,R.id.btn_back2})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_back1:
//                intent.putExtra("data", et_text1.getText().toString().trim());
                intent.putExtra("data", true);
                setResult(100, intent);                     //resultCode用来判断返回结果是否成功
                finish();
                break;
            case R.id.btn_back2:
                intent.putExtra("data", et_text2.getText().toString().trim());
                setResult(100, intent);
                finish();
                break;
            default:
                break;
        }
    }
}
