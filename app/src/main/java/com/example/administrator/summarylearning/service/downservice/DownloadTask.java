package com.example.administrator.summarylearning.service.downservice;

import android.os.AsyncTask;
import android.os.Environment;
import android.print.PrinterId;
import android.util.Log;

import com.example.administrator.summarylearning.secondarylist.adapter.ExpandListAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author      LD
 * @Time        2018/11/26 10:32
 * @Describe    下载任务
 * @Modify
 */
public class DownloadTask extends AsyncTask<String, Integer, Integer> {   //(string:执行任务需传一个参数给后台任务，Integer:显示进度，Integer：区别执行结果)

    //下载状态
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListener downloadListener;      //下载事件监听
    private boolean isCanceled = false;             //是否取消
    private boolean isPaused = false;               //是否暂停
    private int lastProgress;                       //上一次的进度

    public DownloadTask(DownloadListener downloadListener)  {
        this.downloadListener = downloadListener;
    }

    //后台执行具体的下载逻辑（返回的是用于区分执行结果的integer）
    @Override
    protected Integer doInBackground(String... params) {

        InputStream inputStream = null;
        RandomAccessFile savedFile = null;
        File file = null;

        try {
            long downloadLength = 0;    //记录已下载的文件长度
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));                  //根据url解析出下载的文件名
            //String fileName = params[1]+".epub"; 可以填入文件名，但要记得文件的路径要正确书写
            //file = new File(directory+"/"+fileName)

            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();//将文件下载到Environment.DIRECTORY_DOWNLOADS目录下
            file = new File(directory +fileName);
            if (file.exists()) {    //判断是否存在已下载的文件
                downloadLength = file.length();     //若存在则读取已下载字节数
            }
            long contentLength = getContentLength(downloadUrl); //获取待下载文件的总长度
            if (contentLength == 0) {   //说明下载的文件有问题
                return TYPE_FAILED; //下载失败
            } else if (contentLength == downloadLength) {
                //已下载字节和文件总字节数相等，说明已经下载完成了
                return TYPE_SUCCESS;
            }
            //发起网络请求
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")    //告诉服务器想从哪个字节开始下载
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();

            //使用java文件流方式不断从网络上读取数据
            if (response != null) {
                inputStream = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadLength);         //跳过已下载的字节
                byte[] bytes = new byte[1024];
                int total = 0;
                int length;
                while ((length = inputStream.read(bytes)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += length;
                        savedFile.write(bytes, 0, length);
                        //计算已经下载的百分比
                        int progress = (int) ((total + downloadLength) * 100 / contentLength);
                        publishProgress(progress);      //计算当前下载进度进行通知
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }


    //更新当前的下载进度
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            downloadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    //通知最终的下载结果
    //根据参数传入的下载状态进行回调
    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case TYPE_SUCCESS:
                downloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                downloadListener.onFailed();
                break;
            case TYPE_PAUSED:
                downloadListener.onPause();
                break;
            case TYPE_CANCELED:
                downloadListener.onCanceled();
                break;
            default:
                break;
        }
    }



    //暂停操作
    public void pauseDownload(){
        isPaused = true;
    }
    //取消操作
    public void cancelDownload(){
        isCanceled=true;
    }
    //获取文件长度
    private long getContentLength(String  downloadUrl) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if(response!=null && response.isSuccessful()){
            long contentLength = response.body().contentLength();

            response.body().close();//少了这个
            return contentLength;
        }
        return 0;      //返回0表示该文件不存在
    }

}
