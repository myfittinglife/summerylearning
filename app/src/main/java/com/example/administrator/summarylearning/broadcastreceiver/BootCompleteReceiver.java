package com.example.administrator.summarylearning.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
*  @Author      LD
*  @Time        2018/11/28
*  @Describe    静态注册实现开机启动广播(不管用，查找原因)
*  @Modify
*/
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"开机启动",Toast.LENGTH_SHORT).show();
    }
}
