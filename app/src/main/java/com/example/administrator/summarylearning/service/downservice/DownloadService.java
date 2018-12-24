package com.example.administrator.summarylearning.service.downservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.service.ServiceActivity;

import java.io.File;

/**
*  @Author      LD
*  @Time        2018/11/26
*  @Describe    下载服务
*  @Modify
*/
public class DownloadService extends Service {

    private DownloadTask downloadTask;
    private String downloadUrl;

    private DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {

            //通过notify()方法来触发这个通知，更新进度
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            //下载成功时需要将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",-1));
            Toast.makeText(DownloadService.this,"Download Success",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            //下载失败需将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPause() {
            downloadTask = null;
            Toast.makeText(DownloadService.this,"Pause暂停",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this,"Canceled取消",Toast.LENGTH_SHORT).show();
        }
    };

    private DownladBinder mBinder = new DownladBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //为了和活动进行通信
    class DownladBinder extends Binder{

        public void startDownload(String url){  //创建downloadtask实例

            if(downloadTask==null){
                downloadUrl = url;
                downloadTask=new DownloadTask(downloadListener);
                downloadTask.execute(downloadUrl);//开启下载
                /**
                 * downloadTask.execute(downloadUrl,"文件名");//可以填写文件名
                 */
                startForeground(1,getNotification("Downloading...",0));
                Toast.makeText(DownloadService.this,"Downloading...",Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload(){
            if(downloadTask!=null){
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload(){
            if(downloadTask!=null){
                downloadTask.cancelDownload();
            }else {
                if(downloadUrl!=null){
                    //取消下载需将文件删除，并将通知关闭
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    public DownloadService() {
    }

    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService((NOTIFICATION_SERVICE));
    }

    //构建一个用于显示下载的通知
    private Notification getNotification(String title,int progress)
    {
        Intent intent = new Intent(this, ServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);        //使得其意图为点击后打开ServiceActivity活动
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"download");
        builder.setSmallIcon(R.drawable.item_pic)                                   //只能使用纯alpha图层的图片进行设置
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.item_pic2))
                .setContentIntent(pendingIntent)
                .setContentTitle(title);

        if(progress>=0){
            //当progress>=0的时候才需要显示进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }
}
