package com.example.administrator.summarylearning.downloadmanager;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author          LD
 * @Time            2018/11/28
 * @Describe        系统下载管理器调用(不建议使用,因为各个厂商对其功能都进行了限制更改)
 * @Modify
 */
public class DownloadManagerActivity extends AppCompatActivity {

    @BindView(R.id.et_downloadurl)      //下载地址
    EditText etDownloadurl;
    @BindView(R.id.btn_start)           //开始下载
    Button btnStart;
    private DownloadReceiver downloadReceiver;      //广播接收器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager);
        ButterKnife.bind(this);
        downloadReceiver = new DownloadReceiver();
        registerReceiver(downloadReceiver, new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED));
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                if(!TextUtils.isEmpty(etDownloadurl.getText())){
                    downloadFunction(etDownloadurl.getText().toString().trim(),"","下载名字.epub"); //此处一定要加上文件的后缀名
                }
                break;
            default:
                break;
        }
    }

    public void downloadFunction(String downloadurl,String path, String name){

        String downloadPath = "/download";

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadurl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);                       //只允许wifi下载
        request.setDestinationInExternalPublicDir(downloadPath, name);
        request.setTitle("title名字");
        request.setDescription("描述信息");


        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        //进入下载队列
        downloadManager.enqueue(request);



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){//最小版本号大于9
        }else {
            return;
        }

        //在通知栏显示下载进度
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(downloadReceiver);
    }
}
