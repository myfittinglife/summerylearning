package com.example.administrator.summarylearning.interfacecallback;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.okgo.bean.JsonBean1;

import org.w3c.dom.Text;

import java.util.logging.LogRecord;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InterfaceCallbackActivity extends AppCompatActivity implements OnNetFinishListener{

    @BindView(R.id.btn_get_message)
    Button get_message;
    @BindView(R.id.tv_message)
    TextView tv_message;
    Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_callback);
        ButterKnife.bind(this);
        model = new Model((OnNetFinishListener) this);
    }

    @OnClick(R.id.btn_get_message)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_get_message:
                model.getMessage();
                break;
        }
    }

    @Override
    public void onError(JsonBean1 jsonBean1) {
        if(jsonBean1!=null){
            tv_message.setText(jsonBean1.getMessage());
        }
    }

    @Override
    public void onSuccess(JsonBean1 jsonBean1) {
        if(jsonBean1!=null){
            tv_message.setText(jsonBean1.toString());
        }

    }
}
