package com.example.administrator.summarylearning.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;


import com.example.administrator.summarylearning.Constant;
import com.example.administrator.summarylearning.R;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author LD
 * @Time 2018/12/21 17:25
 * @Describe 分享工具，在里面填写分享至朋友圈、微信好友、QQ好友、QQ空间的功能
 * @Modify
 */
public class ShareUtil {

    private IWXAPI iwxapi;
    private Context context;
    private static final int THUMB_SIZE = 150;
    private static final String TAG = "ShareUtil_ceshi";

    private WXMediaMessage mediaMessage;//*共全体使用

    public ShareUtil(Context context) {
        this.context = context;
        WXSign();

    }

    /**
     * 将APP注册
     */
    public void WXSign() {
        if (iwxapi == null) {
            // 通过WXAPIFactory工厂，获取IWXAPI的实例
            iwxapi = WXAPIFactory.createWXAPI(context, Constant.WXAPPID, true);
            // 将应用的appId注册到微信
            iwxapi.registerApp(Constant.WXAPPID);
        }
    }

    //*---------------------------------------------------------------------------------------------
    //分享音乐至/朋友，朋友圈  + 封面图(已封装完成)
    public void shareMusicToWX(String musicUrl, String musicDataUrl, String thumbUrl, String title, String description, int type) {
        if (!iwxapi.isWXAppInstalled()) {
            Toast.makeText(context, "没有安装微信,请先安装微信!", Toast.LENGTH_SHORT).show();
            return;
        }
        //初始化一个WXMusicObject，填写url
        WXMusicObject musicObject = new WXMusicObject();
        musicObject.musicUrl = musicUrl;                 //音频网页的URL地址
        musicObject.musicDataUrl = musicDataUrl;      //音频数据URL地址

        //用 WXMusicObject 对象初始化一个 WXMediaMessage 对象
        mediaMessage = new WXMediaMessage();
        mediaMessage.mediaObject = musicObject;
        mediaMessage.title = title;
        mediaMessage.description = description;
        sendReq(thumbUrl, type);
    }
    /**
     * @param type 分享类型，WXSceneSession聊天界面     WXSceneTimeline朋友圈
     */
    //分享网页至朋友/朋友圈（已弃用）
    public void shareWebpageToWX(String webpageUrl, String title, String description, int type) {
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = webpageUrl;
        mediaMessage = new WXMediaMessage(webpageObject);
        mediaMessage.title = title;
        mediaMessage.description = description;
//        mediaMessage.thumbData = getWXThumb(BitmapFactory.decodeResource(getResources(), R.mipmap.circle_icon, null)).toByteArray();
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //SendMessageToWX.Req.WXSceneTimeline朋友圈    WXSceneSession聊天界面
        req.scene = type;//朋友圈
        req.message = mediaMessage;
        req.transaction = String.valueOf(System.currentTimeMillis());
        iwxapi.sendReq(req);
    }

    //分享网页至朋友/朋友圈  +    封面图设置（已封装完成）
    public void shareWebpageToWX2(String webpageUrl, String thumbUrl, String title, String description, int type) {
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = webpageUrl;
        mediaMessage = new WXMediaMessage(webpageObject);
        mediaMessage.title = title;
        mediaMessage.description = description;
        sendReq(thumbUrl, type);

    }

    //分享视频至朋友/朋友圈（已弃用）
    public void shareVideoToWX(String videoUrl, String title, String description, int type) {
        //初始化一个WXVideoObject，填写url
        WXVideoObject video = new WXVideoObject();
        video.videoUrl = videoUrl;

        //用 WXVideoObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage mediaMessage = new WXMediaMessage(video);
        mediaMessage.title = title;
        mediaMessage.description = description;
        //封面图需要压缩，原图为大图则无法分享
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_pic);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        mediaMessage.thumbData = bitmap2Bytes(thumbBmp, 32);//此处进行压缩

        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = mediaMessage;
        req.scene = type;
        //调用api接口，发送数据到微信
        iwxapi.sendReq(req);
    }

    //分享视频至朋友/朋友圈   +   封面图设置（已封装完成）
    public void shareVideoToWX2(String videoUrl, String thumbUrl, String title, String description, int type) {
        //初始化一个WXVideoObject，填写url
        WXVideoObject video = new WXVideoObject();
        video.videoUrl = videoUrl;

        //用 WXVideoObject 对象初始化一个 WXMediaMessage 对象
        mediaMessage = new WXMediaMessage(video);
        mediaMessage.title = title;
        mediaMessage.description = description;

        sendReq(thumbUrl, type);
    }

    //分享文本至朋友/朋友圈（已完结，不用再改）
    public void shareTextToWX(String text, int type) {
        //初始化一个 WXTextObject 对象，填写分享的文本内容
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        mediaMessage = new WXMediaMessage();
        mediaMessage.mediaObject = textObj;
        mediaMessage.description = text;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = mediaMessage;
        req.scene = type;
        //调用api接口，发送数据到微信
        iwxapi.sendReq(req);

    }

    //*---------------------------------------------------------------------------------------------
    //分享图片至朋友/朋友圈（实现待优化 想想是分享本地图片，还是网络图片）该分享本地的可以
    public void sharePictureToWX(String url, int type) {
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_pic);
        byte[] datas = bitmap2Bytes(bmp, 32);
        Bitmap bitmap = BitmapFactory.decodeByteArray(datas, 0, datas.length);


        //初始化 WXImageObject 和 WXMediaMessage 对象
        WXImageObject imgObj = new WXImageObject(bitmap);
        imgObj.imagePath = "";
        mediaMessage = new WXMediaMessage(imgObj);
        sendReq(url,type);
////        mediaMessage.mediaObject = imgObj;
//
////        //设置缩略图
////        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
////        bmp.recycle();
////        mediaMessage.thumbData = Util.bmpToByteArray(thumbBmp, true);
//
//        //构造一个Req
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = String.valueOf(System.currentTimeMillis());
//        req.message = mediaMessage;
//        req.scene = type;
////        req.userOpenId = getOpenId();
//        //调用api接口，发送数据到微信
//        iwxapi.sendReq(req);
//        sendReq(thumbUrl,type);
    }

    //分享网络图片至朋友/朋友圈 （测试中）未实现！！！
    public void sharePictureToWX2(String imageUrl, int type) {
//        sendReqPic(imageUrl, type);
        fun(imageUrl, type);

    }

    public String getOpenId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("WXLoginData", Context.MODE_PRIVATE);
        String openId = sharedPreferences.getString("openid", "");
        return openId;
    }


    //*----------------------------------工具--------------------------------------------------------
    //带封面图的微信发送封装
    public void sendReq(final String url, final int type) {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                Bitmap bitmap = getBitmap(url);     //获取图片
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 150, 150, true);    //将图片变为150*150
                bitmap.recycle();
                emitter.onNext(thumbBmp);
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Bitmap o) {
                        Log.i(TAG, "onNext: ");
                        mediaMessage.thumbData = bitmap2Bytes(o, 32);//封面图需进行压缩，否则将无法调起微信客户端

                        //构造一个Req
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = mediaMessage;
//                        req.scene = SendMessageToWX.Req.WXSceneTimeline;
                        req.scene = type;
                        //调用api接口，发送数据到微信
                        iwxapi.sendReq(req);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                });
    }

    //根据Url获取Bitmap格式的图片
    public Bitmap getBitmap(String url) {
        URL htmlUrl = null;
        InputStream inStream = null;
        try {
            htmlUrl = new URL(url);
            URLConnection connection = htmlUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(inStream);

    }

    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于maxkb
     *
     * @param bitmap
     * @param maxkb  最大的大小
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxkb && options != 10) {//循环的进行压缩，直至大小小于maxkb
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return output.toByteArray();
    }


    public void fun(final String imageUrl, final int type) {
        Log.i(TAG, "fun: 进入fun1");
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                Bitmap bitmap = getBitmap(imageUrl);     //获取图片
                emitter.onNext(bitmap);//此处发生更改，不再这里转化缩略图
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        Log.i(TAG, "onNext: 此处发送，等待被压缩");
                        fun2(bitmap, type);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void fun2(final Bitmap bitmap, final int type) {
        Log.i(TAG, "fun2: 开始进入");
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                Log.i(TAG, "subscribe: 此处开始压缩");
                byte[] datas = bitmap2Bytes(bitmap, 32);
                Bitmap theBitmap = BitmapFactory.decodeByteArray(datas, 0, datas.length);
                emitter.onNext(theBitmap);
                emitter.onComplete();
            }
        })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        Log.i(TAG, "onNext: 压缩完成，发送朋友圈");
                        WXImageObject imgObj = new WXImageObject(bitmap);
                        imgObj.imagePath = "";
                        mediaMessage = new WXMediaMessage(imgObj);
                        //构造一个Req
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = mediaMessage;
//                        req.scene = SendMessageToWX.Req.WXSceneTimeline;
                        req.scene = type;
                        //调用api接口，发送数据到微信
                        iwxapi.sendReq(req);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //*专门写图片的发送
    public void sendReqPic(final String imageUrl, final int type) {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {

                Bitmap bitmap = getBitmap(imageUrl);     //获取图片
                emitter.onNext(bitmap);//此处发生更改，不再这里转化缩略图
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Bitmap o) {
                        Log.i(TAG, "onNext: ");
//                        Bitmap thumbBmp = Bitmap.createScaledBitmap(o, 150, 150, true);    //将图片变为150*150
//                        mediaMessage.thumbData = bitmap2Bytes(thumbBmp, 32);//封面图需进行压缩，否则将无法调起微信客户端

                        byte[] datas = bitmap2Bytes(o, 32);

                        Bitmap theBitmap = BitmapFactory.decodeByteArray(datas, 0, datas.length);
                        //初始化 WXImageObject 和 WXMediaMessage 对象
                        WXImageObject imgObj = new WXImageObject(theBitmap);
                        imgObj.imagePath = "";
                        mediaMessage = new WXMediaMessage(imgObj);
                        //构造一个Req
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = mediaMessage;
//                        req.scene = SendMessageToWX.Req.WXSceneTimeline;
                        req.scene = type;
                        //调用api接口，发送数据到微信
                        iwxapi.sendReq(req);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                });
    }

    public void sendReqPic2(final String imageUrl, final int type) {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {

                Bitmap bitmap = getBitmap(imageUrl);     //获取图片
                emitter.onNext(bitmap);//此处发生更改，不再这里转化缩略图
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        byte[] datas = bitmap2Bytes(bitmap, 32);
                        Bitmap theBitmap = BitmapFactory.decodeByteArray(datas, 0, datas.length);

                        ObservableEmitter<Bitmap> emitter = new ObservableEmitter<Bitmap>() {
                            @Override
                            public void setDisposable(Disposable d) {

                            }

                            @Override
                            public void setCancellable(Cancellable c) {

                            }

                            @Override
                            public boolean isDisposed() {
                                return false;
                            }

                            @Override
                            public ObservableEmitter<Bitmap> serialize() {
                                return null;
                            }

                            @Override
                            public boolean tryOnError(Throwable t) {
                                return false;
                            }

                            @Override
                            public void onNext(Bitmap value) {

                            }

                            @Override
                            public void onError(Throwable error) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        };
                        emitter.onNext(theBitmap);

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        WXImageObject imgObj = new WXImageObject(bitmap);
                        imgObj.imagePath = "";
                        mediaMessage = new WXMediaMessage(imgObj);
//                        mediaMessage.mediaObject = imgObj;


                        //构造一个Req
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = mediaMessage;
//                        req.scene = SendMessageToWX.Req.WXSceneTimeline;
                        req.scene = type;
                        //调用api接口，发送数据到微信
                        iwxapi.sendReq(req);
                    }
                });
    }


}
