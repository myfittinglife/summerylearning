package com.example.administrator.summarylearning.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.summarylearning.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author
 * @Time
 * @Describe 权限申请学习
 * @Modify
 */
public class PermissionActivity extends AppCompatActivity {

    @BindView(R.id.btn_more_apply)
    Button btnMoreApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_more_apply)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_more_apply:
                if (Build.VERSION.SDK_INT >= 23) {
                    String[] requestPermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
//                    String[] requestPermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,Manifest.permission.GET_ACCOUNTS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW};
//                    String[] requestPermission = new String[]{Manifest.permission.READ_PHONE_STATE};

                    List<String> permissionList = new ArrayList<>();//未申请的权限

                    for(int i = 0;i<requestPermission.length;i++){
                        if (ContextCompat.checkSelfPermission(this, requestPermission[i]) != PackageManager.PERMISSION_GRANTED){
                            permissionList.add(requestPermission[i]);
                        }
                    }
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_LOGS}, 123);//转化为数组后申请

//                    if(permissionList.size()>0){
//                        ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 123);//转化为数组后申请
//                        for(int i = 0;i<permissionList.size();i++){
//                            Log.i("ceshi1211", "未申请的权限为"+ permissionList.get(i));
//                        }
//                    }else {
//                        Toast.makeText(getApplicationContext(),"权限都已申请",Toast.LENGTH_SHORT).show();
//                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 123:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "权限未全部申请退出", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    //8全部申请，进行操作
                    Toast.makeText(this, "权限全部申请,进行下一步操作", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "发生位置错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
