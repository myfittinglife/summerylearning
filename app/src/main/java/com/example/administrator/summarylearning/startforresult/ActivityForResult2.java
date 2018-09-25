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

public class ActivityForResult2 extends AppCompatActivity {

    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.et_text)
    EditText et_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result2);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                Intent intent = new Intent();
                intent.putExtra("data", et_text.getText().toString().trim());
                setResult(100, intent);
                finish();
                break;
            default:
                break;
        }
    }
}
