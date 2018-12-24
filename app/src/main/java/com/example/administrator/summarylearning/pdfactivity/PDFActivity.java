package com.example.administrator.summarylearning.pdfactivity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author LD
 * @Time 2018/12/15
 * @Describe PDF文件查看
 * @Modify
 */
public class PDFActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.et_url)              //pdf地址
            EditText etUrl;
    @BindView(R.id.btn_loadpdf)         //加载pdf
            Button btnLoadpdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        ButterKnife.bind(this);
        initView();

    }

    public void initView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
    }

    public void initData(String pdfUrl) {
        webView.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file=" + pdfUrl);
    }

    @OnClick(R.id.btn_loadpdf)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_loadpdf:
                if(!TextUtils.isEmpty(etUrl.getText())){
                    initData(etUrl.getText().toString());
                }else {
                    Toast.makeText(this,"请输入地址",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
