package com.example.administrator.summarylearning.downloadmanager;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



/**
 * @Author          LD
 * @Time            2018/11/28 10:54
 * @Describe        DownloadManager下载的广播接收器，用来监听其点击和取消下载的事件
 * @Modify
 */
public class DownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("1128ceshi", "onReceive: 监听到广播");
//        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if(DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.getAction())){//点击事件
//            Intent intent1 = context.getPackageManager().getLaunchIntentForPackage("com.android.providers.downloads.ui");
//            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent1);
            Log.i("1128ceshi", "onReceive: 点击事件");

        }

    }
}
