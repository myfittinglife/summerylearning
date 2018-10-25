package com.example.administrator.summarylearning.text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.github.lzyzsd.jsbridge.BridgeWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextActivity extends AppCompatActivity {

    @BindView(R.id.jsbridge_webview)
    BridgeWebView jsbridge_webview;
    @BindView(R.id.common_webview)
    WebView commonWebview;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_change)
    Button btnChange;
    private boolean isjsbridgechoosed = true;        //是否选择了jsbridge
    private int choose; //  0jsbridge 1 webview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);

        WebSettings webSettings = jsbridge_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);         //是否允许执行JavaScript脚本，默认false
        jsbridge_webview.loadUrl("https://www.baidu.com/");


        WebSettings webSettings2 = commonWebview.getSettings();
//        webSettings2.setJavaScriptEnabled(true);         //是否允许执行JavaScript脚本，默认false
        commonWebview.loadUrl("https://www.baidu.com/");

    }
    @OnClick(R.id.btn_change)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_change:
                isjsbridgechoosed=isjsbridgechoosed==true?false:true;
                tvName.setText(isjsbridgechoosed==true?"↑BridgeWebView":"↓Webview");
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if(isjsbridgechoosed){
            if (jsbridge_webview.canGoBack()) {
                jsbridge_webview.goBack();
                return;
            }
        }else {
            if (commonWebview.canGoBack()) {
                commonWebview.goBack();
                return;
            }
        }



        super.onBackPressed();
    }
}
