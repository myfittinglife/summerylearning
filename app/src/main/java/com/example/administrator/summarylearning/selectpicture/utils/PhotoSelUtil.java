package com.example.administrator.summarylearning.selectpicture.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;


import com.example.administrator.summarylearning.selectpicture.Glide4Engine;
import com.example.administrator.summarylearning.utils.PermissionUtil;
import com.example.administrator.summarylearning.utils.ToastUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

/**
 * 图片选择工具类
 * 主要对Matisse的一些参数进行设定
 */
public class PhotoSelUtil {

    private  Activity context;
    private  int size=1;       //最大选择数
    public static final int REQUEST_CODE_CHOOSE = 300;//照片选择回调

    public PhotoSelUtil(Activity context) {
        this.context = context;
    }

    public void sel(){
        boolean writeandread = PermissionUtil.verifyReadAndWritePermissions(context);
        boolean takephoto = PermissionUtil.verifyTakePhoto(context);
        if (!writeandread) {
            ToastUtil.showToast(context, "请开启读写权限");
            return;
        }
        if (!takephoto) {
            ToastUtil.showToast(context, "请开启照相和录音权限");
            return;
        }

        Matisse.from(context)
                .choose(MimeType.ofImage())                                         //类型
                .countable(true)
                .maxSelectable(size)                                             //最大选择数
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)  //选择方向
                .theme(com.zhihu.matisse.R.style.Matisse_Zhihu)                     //主题
                .imageEngine(new Glide4Engine())                                    //图片引擎，glide4请用自定义的，旧版直接GlideEngine（）就行
                .forResult(REQUEST_CODE_CHOOSE);
    }

}
