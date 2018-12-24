package com.example.administrator.summarylearning.jsbridge;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.text.TextActivity;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.WebViewJavascriptBridge;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 混合编程 使用lzyzsd/JsBridge框架
 * Website:https://github.com/lzyzsd/JsBridge
 */
public class JsBridgeActivity extends AppCompatActivity {


    @BindView(R.id.bridgewebview)
    BridgeWebView bridgewebview;
    @BindView(R.id.btn_loadurl)
    Button btnLoadurl;
    @BindView(R.id.btn_goto)
    Button btnGoto;

    private static final String TAG = "JsBridgeActivity_ceshi";
    
    
    

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_bridge);
        ButterKnife.bind(this);
        configuration();


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void configuration() {        //WebView配置函数
        WebSettings webSettings = bridgewebview.getSettings();
        webSettings.setJavaScriptEnabled(true);         //是否允许执行JavaScript脚本，默认false
//        webSettings.setDomStorageEnabled(true);

//        webSettings.setAllowFileAccessFromFileURLs(true);   // 设置是否允许 WebView 使用 File 协议(看简书介绍)     // 默认设置为true，即允许在 File 域下执行任意 JavaScript 代码


//        bridgewebview.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return super.shouldOverrideUrlLoading(view, request);
//            }
//        });


    }

    @OnClick({R.id.btn_loadurl,R.id.btn_goto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_loadurl:
                bridgewebview.loadUrl("https://www.baidu.com/");
                break;
            case R.id.btn_goto:
                Intent intent = new Intent(this, TextActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(bridgewebview.canGoBack()){          //webview回退可以返回到上一页
            bridgewebview.goBack();
            return;
        }
        super.onBackPressed();
    }
    //Java中定义方法供JS调用
    public void fun(){
        bridgewebview.registerHandler("methodName", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i(TAG, "methodName()"+"从JS返回来的数据为"+data);
                function.onCallBack("submitFromWeb exe, response data from Java");

            }
        });
    }
    /**
     * 在JS中调用此方法：
     WebViewJavascriptBridge.callHandler(
            'methodName',
            {'param',str1},
            function(responseData){
            document.getElementById("show").innerHTML="send get responseData from java, data = "+reponseData;//返回表格行的开始和结束标签之间的 HTML
     });




     */



}
