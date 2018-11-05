package com.example.administrator.summarylearning.littlefeature.judgeinstall;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.summarylearning.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者    LD
 * 时间    2018.11.5  17:50
 * 描述    判断是否安装某应用
 */
public class JudgeInstallActivity extends AppCompatActivity {

    @BindView(R.id.btn_judge)           //判断是否含有某应用
            Button btnJudge;
    @BindView(R.id.et_packagename)      //包名
            EditText etPackagename;
    @BindView(R.id.btn_getPackagename)  //获取已安装应用包名
    Button btnGetPackagename;
    @BindView(R.id.tv_installedname)    //显示已安装应用包名
    TextView tvInstalledname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge_install);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_judge,R.id.btn_getPackagename})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_judge:
                if (etPackagename.getText().toString() == "" || etPackagename.getText().toString() == null) {
                    Toast.makeText(getApplicationContext(), "请输入包名", Toast.LENGTH_SHORT).show();
                } else {
                    if (isInstallByread(etPackagename.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "安装了此应用", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "未安装此应用", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_getPackagename:   //获取应用包名
                getInstalledPackage();
                break;
        }

    }

    //判断是否安装了此应用
    private boolean isInstallByread(String packageName) {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);        //获取已安装程序的包信息
        List<String> packageNames = new ArrayList<String>();
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有为true，无为false
        return packageNames.contains(packageName);
    }

    private void getInstalledPackage(){
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if(packageInfos!=null){
            for(int i = 0;i<packageInfos.size();i++){
                String appName = packageManager.getApplicationLabel(packageInfos.get(i).applicationInfo).toString();
                String applicationName = packageInfos.get(i).packageName;
                tvInstalledname.append("\n"+appName+applicationName);
            }
        }
    }
}
