package com.example.administrator.summarylearning.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * @Author      LD
 * @Time        2018/12/21 17:25
 * @Describe    分享工具，在里面填写分享至朋友圈、微信好友、QQ好友、QQ空间的功能
 * @Modify
 */
public class ShareUtil {

    private IWXAPI iwxapi;
    private Context context;
    private static final int THUMB_SIZE = 150;
    private static final String TAG = "ShareUtil_ceshi";

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

    //分享音乐至/朋友，朋友圈(朋友圈未实现)
    public void shareMusicToWX(String musicUrl,String musicDataUrl,int type){
        if (!iwxapi.isWXAppInstalled()) {
            Toast.makeText(context, "没有安装微信,请先安装微信!", Toast.LENGTH_SHORT).show();
            return;
        }
        //初始化一个WXMusicObject，填写url
        WXMusicObject musicObject = new WXMusicObject();
        musicObject.musicUrl=musicUrl;                 //音频网页的URL地址
        musicObject.musicDataUrl = musicDataUrl;      //音频数据URL地址

        

        //用 WXMusicObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = musicObject;
        msg.title = "张杰";
        msg.description = "他不懂";
//        Bitmap thumbBmp = BitmapFactory.decodeResource(getResources(), R.drawable.send_music_thumb);
//        //设置音乐缩略图
//        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
        msg.thumbData = null;

        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = type;
//        req.scene = SendMessageToWX.Req.WXSceneSession;
//        req.userOpenId = getOpenId();//okBIj5osfWv6G_y9hj_re2CRtogg
        iwxapi.sendReq(req);
//        if(getOpenId().equals("")||getOpenId()==null){
//            Toast.makeText(context,"请先登录微信",Toast.LENGTH_SHORT).show();
//            Log.i(TAG, "shareToWXCircle: 请先登录微信");
//        }else {
//            //调用api接口，发送数据到微信
//            Log.i(TAG, "shareToWXCircle: 发送至微信");
//            iwxapi.sendReq(req);
//        }



    }

    /**
     *
     * @param type  分享类型，WXSceneSession聊天界面     WXSceneTimeline朋友圈
     */
    //分享网页至朋友/朋友圈
    public void shareWebpageToWX(String webpageUrl,int type){
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = webpageUrl;
        WXMediaMessage mediaMessage = new WXMediaMessage(webpageObject);
        mediaMessage.title = "只要平凡";
        mediaMessage.description = "张杰";
//        mediaMessage.thumbData = getWXThumb(BitmapFactory.decodeResource(getResources(), R.mipmap.circle_icon, null)).toByteArray();
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //SendMessageToWX.Req.WXSceneTimeline朋友圈    WXSceneSession聊天界面
        req.scene = type;//朋友圈
        req.message = mediaMessage;
        req.transaction = String.valueOf(System.currentTimeMillis());
        iwxapi.sendReq(req);
    }
    
    //分享视频至朋友/朋友圈
    public void shareVideoToWX(String videoUrl,String title,String description,int type){
        //初始化一个WXVideoObject，填写url
        WXVideoObject video = new WXVideoObject();
        video.videoUrl =videoUrl;

        //用 WXVideoObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage mediaMessage = new WXMediaMessage(video);
        mediaMessage.title =title;
        mediaMessage.description =description;
        //封面图需要压缩，原图为大图则无法分享
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_pic);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        mediaMessage.thumbData = bitmap2Bytes(thumbBmp, 32);//此处进行压缩


        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message =mediaMessage;
        req.scene = type;
        //调用api接口，发送数据到微信
        iwxapi.sendReq(req);
    }
    //分享视频至朋友/朋友圈   +   封面图设置
    public void shareVideoToWX2(String videoUrl,String thumbUrl,String title,String description,int type){
        //初始化一个WXVideoObject，填写url
        WXVideoObject video = new WXVideoObject();
        video.videoUrl =videoUrl;

        //用 WXVideoObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage mediaMessage = new WXMediaMessage(video);
        mediaMessage.title =title;
        mediaMessage.description =description;



        //封面图需要压缩，原图为大图则无法分享
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_pic);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        mediaMessage.thumbData = bitmap2Bytes(thumbBmp, 32);//此处进行压缩


        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message =mediaMessage;
        req.scene = type;
        //调用api接口，发送数据到微信
        iwxapi.sendReq(req);
    }


    //分享文本至朋友/朋友圈
    public void shareTextToWX(String text, int type){
        //初始化一个 WXTextObject 对象，填写分享的文本内容
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message =msg;
        req.scene = type;
        //调用api接口，发送数据到微信
        iwxapi.sendReq(req);

    }

    //分享图片至朋友/朋友圈
    public void sharePictureToWX(String url,int type){
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_pic);

        //初始化 WXImageObject 和 WXMediaMessage 对象
        WXImageObject imgObj = new WXImageObject(bmp);
        imgObj.imagePath = "";
        WXMediaMessage mediaMessage = new WXMediaMessage();
        mediaMessage.mediaObject = imgObj;

//        //设置缩略图
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
//        bmp.recycle();
//        mediaMessage.thumbData = Util.bmpToByteArray(thumbBmp, true);

        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = mediaMessage;
        req.scene = type;
//        req.userOpenId = getOpenId();
        //调用api接口，发送数据到微信
        iwxapi.sendReq(req);
    }






    public String getOpenId(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("WXLoginData",Context.MODE_PRIVATE);
        String openId = sharedPreferences.getString("openid","");
        return openId;
    }

    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于maxkb
     * @param bitmap
     * @param maxkb     最大的大小
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxkb&& options != 10) {//循环的进行压缩，直至大小小于maxkb
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return output.toByteArray();
    }

    
}
