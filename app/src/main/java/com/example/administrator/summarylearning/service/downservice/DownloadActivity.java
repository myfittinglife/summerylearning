package com.example.administrator.summarylearning.service.downservice;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.summarylearning.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author
 * @Time 2018/11/26
 * @Describe 下载
 * @Modify
 */
public class DownloadActivity extends AppCompatActivity {

    @BindView(R.id.et_url)
    EditText etUrl;
    @BindView(R.id.btn_start_download)
    Button btnStartDownload;
    @BindView(R.id.btn_pause_download)
    Button btnPauseDownload;
    @BindView(R.id.btn_cancel_download)
    Button btnCancelDownload;

    private DownloadService.DownladBinder downloadBinder;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);//启动服务
        bindService(intent, connection, BIND_AUTO_CREATE);  //绑定服务
        if (ContextCompat.checkSelfPermission(DownloadActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DownloadActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        url = etUrl.getText().toString();


    }

    //创建匿名内部类，获取DownloadBinder实例，有了这个实例，就可以在活动中调用服务中的各种方法了
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownladBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @OnClick({R.id.btn_start_download, R.id.btn_pause_download, R.id.btn_cancel_download})
    public void onViewClicked(View view) {
        if (downloadBinder == null) {
            return;
        } else {
            switch (view.getId()) {
                case R.id.btn_start_download:
                    if (!TextUtils.isEmpty(etUrl.getText())) {
                        downloadBinder.startDownload(etUrl.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "请输入下载地址", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_pause_download:                   //停止下载
                    downloadBinder.pauseDownload();
                    break;
                case R.id.btn_cancel_download:                  //取消下载
                    downloadBinder.cancelDownload();
                    break;
                default:
                    break;
            }
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "拒绝权限将无法使用该程序", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
