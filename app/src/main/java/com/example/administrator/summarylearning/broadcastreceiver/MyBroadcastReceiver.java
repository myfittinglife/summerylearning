package com.example.administrator.summarylearning.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
*  @Author      LD
*  @Time        2018/11/28
*  @Describe    标准广播监听器
*  @Modify
*/
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"接收到标准广播",Toast.LENGTH_SHORT).show();

    }
}
