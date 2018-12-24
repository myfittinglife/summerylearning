package com.example.administrator.summarylearning.mixdevelopment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.summarylearning.MainActivity;
import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者    LD
 * 修改
 * 时间    2018.11.12
 * 描述    混合开发（使用webview，并实现交互）
 */
public class MixedDevelopActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.btn_runJsfunc)     //无参调用js
    Button btnRunJsfunc;
    @BindView(R.id.et_text)         //传给js的参数
    EditText etToJsText;
    @BindView(R.id.btn_sendtoJS)    //有参调用js
    Button btnSendtoJS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixed_develop);
        ButterKnife.bind(this);
        initWeb();
    }


    public void initWeb() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);     //运行执行JavaScript
        webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {//这个方法是在5.0以下使用的
                view.loadUrl(String.valueOf(request.getUrl()));
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {//这个方法是在5.0以上的版本上使用的
                view.loadUrl(url);
                return true;
            }
        });
//        webview.loadUrl("file:///android_asset/demo.html");        //加载本地html
        webView.loadUrl("file:///android_asset/myhtml.html");        //加载本地html
        webView.addJavascriptInterface(MixedDevelopActivity.this,"android");


    }


    @OnClick({R.id.btn_runJsfunc,R.id.btn_sendtoJS})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_runJsfunc:        //无参调用js中函数
                RunJsFunction();
                break;
            case R.id.btn_sendtoJS:         //有参调用js
                RunJsFunctionWithParameters();
                break;
        }
    }
    //*-----------------------调用JS，给JS传递参数----------------------------------
    //1、无参调用JS
    public void RunJsFunction() {
        if (Build.VERSION.SDK_INT > 18) {
            webView.evaluateJavascript("javascript:javaCallJs()", new ValueCallback<String>() {//效率高，但是这个方法是在API19才可以使用的
                @Override
                public void onReceiveValue(String value) {
                    Log.i("done", "onReceiveValue: " + value);
                    if(Looper.myLooper()!=Looper.getMainLooper()){
                        Log.i("1112ceshi", "startFunction: 非主线程");
                    }else {
                        Log.i("1112ceshi", "startFunction: 主线程");
                    }
                }
            });
        } else {
            webView.loadUrl("javascript:javaCallJs()");//但是会刷新相应的页面
        }
    }
    //2、有参调用JS
    public void RunJsFunctionWithParameters(){
        String data = etToJsText.getText().toString();
        webView.loadUrl("javascript:javaCallJsWith(" + "'"+data+"'" + ")");// 传递参数调用
//        webView.loadUrl("javascript:javaCallJsWith(" + "'我是Java的参数'" + ")");// 传递参数调用

    }

    //*-----------------------JS调用Android,给Android传递参数-------------------
    //由于安全原因 需要加 @JavascriptInterface
    @JavascriptInterface
    public void startFunction(){    //这是在子线程中的
        if(Looper.myLooper()!=Looper.getMainLooper()){
            Log.i("1112ceshi", "startFunction: 非主线程");
        }else {
            Log.i("1112ceshi", "startFunction: 主线程");
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MixedDevelopActivity.this,"我来自JAVA",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @JavascriptInterface
    public void startFunction(final String text){   //这是在子线程中的
        if(Looper.myLooper()!=Looper.getMainLooper()){
            Log.i("1112ceshi", "startFunction: 非主线程");
        }else {
            Log.i("1112ceshi", "startFunction: 主线程");
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MixedDevelopActivity.this).setMessage(text).show();
            }
        });
    }



}
