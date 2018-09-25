package com.example.administrator.summarylearning.okgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.okgo.bean.JsonBean1;
import com.lzy.okgo.OkGo;

import com.lzy.okgo.adapter.Call;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 配置主要：1、implementation 'com.lzy.net:okgo:3.0.4'
 * 2、全局配置：新建ProjectApp继承application，在Manifest中android:name=".ProjectApp"此文件，只需配置一次即可
 * 3、自定义解析JsonCallBack
 */
public class ActivityOkGo extends AppCompatActivity {


    @BindView(R.id.btn_getstring)
    Button btn_getstring;

    @BindView(R.id.btn_getjson)
    Button btn_getjson;
    @BindView(R.id.tv_content)
    TextView tv_content;


    private String url1 = "http://www.wanandroid.com/tools/mockapi/10592/string";
    /*返回内容
    String字符串
     */
    private String url2 = "http://www.wanandroid.com/tools/mockapi/10592/json1";

    /*返回的内容
     {
    "msg": "验证码发送成功",
    "isError": false,
    "isOk": true,
    "message": null,
    "status": "300"
    }
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_go);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_getjson, R.id.btn_getstring})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_getstring:
                OkGo.<String>get(url1)                           //自带的String解析   get用post不行
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                tv_content.setText(response.body());
                            }

                            @Override
                            public void onError(Response<String> response) {
                                tv_content.setText("获取错误");
                            }
                        });
                break;
            case R.id.btn_getjson:
                OkGo.<JsonBean1>get(url2)
                        .execute(new JsonCallBack<JsonBean1>() {
                            @Override
                            public void onSuccess(Response<JsonBean1> response) {               //UI线程中
                                if (response != null && response.body() != null) {
                                    tv_content.setText(response.body().toString());             //？怎么实现字段为空就自动转化为“null”的？
                                }
                            }

                            @Override
                            public void onError(Response<JsonBean1> response) {
                                tv_content.setText("获取错误");
                            }
                        });
                break;
            default:
                break;
        }

    }

}
