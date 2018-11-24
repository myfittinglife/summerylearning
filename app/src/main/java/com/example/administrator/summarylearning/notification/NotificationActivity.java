package com.example.administrator.summarylearning.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author LD
 * @Time 2018/11/23
 * @Describe 通知广播功能，适配8.0之后的通知渠道
 * @Modify
 */
public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.btn_sendmessage)     //4
    Button btnSendmessage;
    @BindView(R.id.btn_subscribe)       //3
    Button btnSubscribe;
    @BindView(R.id.btn_newmessage)      //2
    Button btnSendNews;
    @BindView(R.id.btn_send1)           //1
    Button btnSend1;
    @BindView(R.id.btn_send0)           //0
    Button btnSend0;
    @BindView(R.id.btn_send5)           //5
    Button btnSend5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";                                                              //渠道ID
            String channelName = "聊天消息";                                                         //渠道名称
            int importance = NotificationManager.IMPORTANCE_HIGH;                                   //重要等级4
            createNotificationChannel(channelId, channelName, importance);
            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;                                    //重要等级3
            createNotificationChannel(channelId, channelName, importance);
            channelId = "newsinfo";
            channelName = "新闻资讯";
            importance = NotificationManager.IMPORTANCE_LOW;                                        //重要等级2
            createNotificationChannel(channelId, channelName, importance);
            channelId = "level1";
            channelName = "等级1消息";
            importance = NotificationManager.IMPORTANCE_MIN;                                       //重要等级1
            createNotificationChannel(channelId, channelName, importance);
            channelId = "level0";
            channelName = "等级0消息";
            importance = NotificationManager.IMPORTANCE_NONE;                                       //重要等级0
            createNotificationChannel(channelId, channelName, importance);
            channelId = "level5";
            channelName = "等级5消息";
            importance = NotificationManager.IMPORTANCE_MAX;                                        //重要等级5
            createNotificationChannel(channelId, channelName, importance);



        }
    }

    /**
     * @param channelId
     * @param channelName
     * @param importance  创建一个通知渠道至少需要渠道ID、渠道名称以及重要等级这三个参数
     *                    渠道ID可以随便定义，只要保证全局唯一性就可以
     *                    渠道名称是给用户看的，需要能够表达清楚这个渠道的用途
     *                    重要等级的不同则会决定通知的不同行为，当然这里只是初始状态下的重要等级，用户可以随时手动更改某个渠道的重要等级，App是无法干预的。
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {

        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(true);      //允许该通知渠道下的通知显示角标
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    @OnClick({R.id.btn_sendmessage, R.id.btn_subscribe, R.id.btn_newmessage,R.id.btn_send5,R.id.btn_send1,R.id.btn_send0})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sendmessage:
                sendChatMessage();                       //发送聊天消息4
                break;
            case R.id.btn_subscribe:
                sendSubscribeMessage();                  //发送订阅消息3
                break;
            case R.id.btn_newmessage:
                sendNewsInfomation();                    //发送新闻消息2
                break;
            case R.id.btn_send1:                         //发送等级1消息
                sendCommonMessage("level1","level1标题","level1内容",1);
                break;
            case R.id.btn_send0:                         //发送等级0消息
                sendCommonMessage("level0","level0标题","level0内容",0);
                break;
            case R.id.btn_send5:                         //发送等级5消息
                sendCommonMessage("level5","level5标题","level5内容",5);
                break;
            default:
                break;
        }
    }

    //聊天信息4
    public void sendChatMessage() {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //若通知渠道被用户关闭了，则让用户跳转至设置通知渠道的界面来更改其通知渠道的权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel("chat");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();

            }
        }


        Notification notification = new NotificationCompat.Builder(this, "chat")        //多了个通知渠道
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.item_pic)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.app_icon))
                .setAutoCancel(true)
                .setNumber(1)                 //显示通知数目
                .build();
        manager.notify(4, notification);
    }
    //订阅消息3
    public void sendSubscribeMessage() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //若通知渠道被用户关闭了，则让用户跳转至设置通知渠道的界面来更改其通知渠道的权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {                                           //是>=，8.0及以上版本
            NotificationChannel channel = manager.getNotificationChannel("subscribe");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();

            }
        }


        Notification notification = new NotificationCompat.Builder(this, "subscribe")        //多了个通知渠道
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁沿线30万商铺抢购中！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.item_pic)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.app_icon))
                .setAutoCancel(true)
                .setNumber(2)
                .build();
        manager.notify(3, notification);
    }
    //新闻消息2
    public void sendNewsInfomation() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        //若通知渠道被用户关闭了，则让用户跳转至设置通知渠道的界面来更改其通知渠道的权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel("newsinfo");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();

            }
        }
        Notification notification = new NotificationCompat.Builder(this, "newsinfo")        //多了个通知渠道
                .setContentTitle("收到一条新闻资讯")
                .setContentText("据路透社消息...")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.item_pic)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.app_icon))
                .setAutoCancel(true)
                .setNumber(3)
                .build();
        manager.notify(2, notification);

    }


    /**
     * 通用的发送消息方法
     * @param channelName           渠道名称
     * @param contentTitle          内容标题
     * @param ContentText           内容主体
     * @param notifyNum             通知id
     */
    public void sendCommonMessage(String channelName,String contentTitle,String ContentText,int notifyNum){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //若通知渠道被用户关闭了，则让用户跳转至设置通知渠道的界面来更改其通知渠道的权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel(channelName);
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();

            }
        }


        Notification notification = new NotificationCompat.Builder(this, channelName)        //多了个通知渠道
                .setContentTitle(contentTitle)
                .setContentText(ContentText)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.item_pic)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.app_icon))
                .setAutoCancel(true)
                .setNumber(1)                 //显示通知数目
                .build();
        manager.notify(notifyNum, notification);
    }

}

