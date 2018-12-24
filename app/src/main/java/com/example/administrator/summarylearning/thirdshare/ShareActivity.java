package com.example.administrator.summarylearning.thirdshare;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.administrator.summarylearning.Constant;
import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.utils.ShareUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.System.gc;

/**
 * @Author LD
 * @Time 2018/12/11
 * @Describe 各种分享汇总
 * @Modify
 */
public class ShareActivity extends AppCompatActivity {

    @BindView(R.id.btn_share_friend)
    Button btnShareFriend;
    @BindView(R.id.btn_share_circle)
    Button btnShareCircle;
    @BindView(R.id.btn_share_music_circle)      //分享音乐至朋友圈
            Button btnShareMusicCircle;
    @BindView(R.id.btn_share_web_circle)
    Button btnShareWebCircle;
    @BindView(R.id.btn_share_music_friend)
    Button btnShareMusicFriend;
    @BindView(R.id.btn_share_web_friend)
    Button btnShareWebFriend;
    @BindView(R.id.btn_share_video_circle)
    Button btnShareVideoCircle;
    @BindView(R.id.btn_share_video_friend)
    Button btnShareVideoFriend;
    @BindView(R.id.btn_share_text_circle)
    Button btnShareTextCircle;
    @BindView(R.id.btn_share_text_friend)
    Button btnShareTextFriend;
    @BindView(R.id.btn_share_pic_circle)
    Button btnSharePicCircle;
    @BindView(R.id.btn_share_pic_friend)
    Button btnSharePicFriend;
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI wxapi;
    private Bitmap mybitmap;
    private WXMediaMessage mediaMessage;

    private ShareUtil shareUtil;
    //分享音乐的地址
    private String musicWebUrl = "https://y.qq.com/n/yqq/song/004OGc2e1I9J7F.html";
    private String musicUrl = "http://221.204.62.19/amobile.music.tc.qq.com/C400003Q7qbu2pgPK8.m4a?guid=2616287232&amp;vkey=D12FEFCD5DEF015BA8DD1ABAE5AAEBC0D85CE62CFEAB2A20540D026E58C0A9985A6AC5D4798E6E5BACDCEC391F8F01B107E3BAA5649CD10F&amp;uin=0&amp;fromtag=66";
    //分享网页的地址
    private String webpageUrl = "https://www.baidu.com/";
    //分享视频的地址 只要平凡-张杰
    private String videoUrl = "http://sochy.tcdn.qq.com/vcloud1049.tc.qq.com/1049_M0119900003sdHF20sPcJH1001574514.f40.mp4?vkey=DE930D4982A6B7751CC85F81F499ACFFD2443C42C7FDEE5096C820DEE421581E55447777B0872E9CC35B3F9CD775939542D4E56B8A28A8903B4358598B22F7F1EF7813B5A6C6D6A772970A325C59B1618C2B2F5AF75B8C18&ocid=2438469036";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        shareUtil = new ShareUtil(this);
    }

    @OnClick({R.id.btn_share_friend, R.id.btn_share_circle, R.id.btn_share_music_circle, R.id.btn_share_web_circle, R.id.btn_share_music_friend, R.id.btn_share_web_friend
            , R.id.btn_share_video_circle, R.id.btn_share_video_friend, R.id.btn_share_text_circle, R.id.btn_share_text_friend,R.id.btn_share_pic_circle,R.id.btn_share_pic_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_share_friend:         //分享给朋友
//                WXSign();
//                if (!wxapi.isWXAppInstalled()) {
//                    Toast.makeText(this, "没有安装微信,请先安装微信!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.circle_icon, null);//资源文件转为bitmap
//                WXWebpageObject webpageObject = new WXWebpageObject();
//                webpageObject.webpageUrl = "https://www.baidu.com/";
//                WXMediaMessage msg = new WXMediaMessage(webpageObject);
//                msg.title = "标题";
//                msg.description = "描述信息";
//                msg.thumbData = getWXThumb(bitmap).toByteArray();   //此处放一个bitmap图像
//                SendMessageToWX.Req req = new SendMessageToWX.Req();
//                //WXSceneTimeline朋友圈    WXSceneSession聊天界面
////                req.scene = isTimeLineCb ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;//聊天界面
//                req.scene = SendMessageToWX.Req.WXSceneSession;//聊天界面
//                req.message = msg;
//                req.transaction = String.valueOf(System.currentTimeMillis());
//                wxapi.sendReq(req);
                break;
            case R.id.btn_share_circle:         //分享至朋友圈
                WXSign();
                if (!wxapi.isWXAppInstalled()) {
                    Toast.makeText(this, "没有安装微信,请先安装微信!", Toast.LENGTH_SHORT).show();
                    return;
                }

                WXWebpageObject webpageObject = new WXWebpageObject();
                webpageObject.webpageUrl = "https://www.baidu.com/";
                WXMediaMessage mediaMessage = new WXMediaMessage(webpageObject);
                mediaMessage.title = "分享头";
                mediaMessage.description = "分享描述";
                mediaMessage.thumbData = getWXThumb(BitmapFactory.decodeResource(getResources(), R.mipmap.circle_icon, null)).toByteArray();
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                //WXSceneTimeline朋友圈    WXSceneSession聊天界面
//                req.scene = isTimeLineCb ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;//聊天界面
                req.scene = SendMessageToWX.Req.WXSceneTimeline;//朋友圈
                req.message = mediaMessage;
                req.transaction = String.valueOf(System.currentTimeMillis());
                wxapi.sendReq(req);
                break;
            case R.id.btn_share_music_circle:       //分享音乐至朋友圈
                shareUtil.shareMusicToWX(musicWebUrl, musicUrl, SendMessageToWX.Req.WXSceneTimeline);
                break;
            case R.id.btn_share_music_friend:       //分享音乐给朋友
                shareUtil.shareMusicToWX(musicWebUrl, musicUrl, SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.btn_share_web_circle:         //分享网页至朋友圈
                shareUtil.shareWebpageToWX(webpageUrl, SendMessageToWX.Req.WXSceneTimeline);
                break;
            case R.id.btn_share_web_friend:         //分享网页给朋友
                shareUtil.shareWebpageToWX(webpageUrl, SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.btn_share_video_circle:       //分享视频至朋友圈
                shareUtil.shareVideoToWX(videoUrl, "只要平凡","张杰",SendMessageToWX.Req.WXSceneTimeline);
                break;
            case R.id.btn_share_video_friend:       //分享视频给朋友
                shareUtil.shareVideoToWX(videoUrl, "只要平凡","张杰",SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.btn_share_text_circle:        //分享文本至朋友圈
                shareUtil.shareTextToWX("测试分享文本", SendMessageToWX.Req.WXSceneTimeline);
                break;
            case R.id.btn_share_text_friend:        //分享文本给朋友
                shareUtil.shareTextToWX("测试分享文本", SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.btn_share_pic_circle:         //分享图片至朋友圈
                shareUtil.sharePictureToWX("",SendMessageToWX.Req.WXSceneTimeline);
                break;
            case R.id.btn_share_pic_friend:         //分享图片给朋友
                shareUtil.sharePictureToWX("",SendMessageToWX.Req.WXSceneSession);
                break;


            default:
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mybitmap != null) {
                Bitmap thumBmp = Bitmap.createScaledBitmap(mybitmap, 100, 100, true);//图片大小有限制，太大分享不了
                mediaMessage.thumbData = bmpToByteArray(thumBmp, true);
            }
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = mediaMessage;
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
            wxapi.sendReq(req);
        }
    };


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将APP注册
     */
    public void WXSign() {
        if (wxapi == null) {
            // 通过WXAPIFactory工厂，获取IWXAPI的实例
            wxapi = WXAPIFactory.createWXAPI(this, Constant.WXAPPID, true);
            // 将应用的appId注册到微信
            wxapi.registerApp(Constant.WXAPPID);
        }

    }


    public static final int IMAGE_LENGTH_LIMIT = 6291456;

    private static ByteArrayOutputStream getWXThumb(Bitmap resource) {

        Bitmap thumb = createScaledBitmap(resource, 100, 100, true);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int quality = 90;
        int realLength = Util.getBitmapByteSize(resource.getWidth(), resource.getHeight(), Bitmap.Config.ARGB_8888);
        if (realLength > IMAGE_LENGTH_LIMIT) {
            quality = (int) (IMAGE_LENGTH_LIMIT * 1f / realLength * 100);
        }
        if (quality < 75) {
            quality = 75;
        }
        resource.compress(Bitmap.CompressFormat.PNG, quality, output);
        output.reset();
        thumb.compress(Bitmap.CompressFormat.PNG, 85, output);
        return output;
    }

    /**
     * 修改图片的大小(从当前存在的位图，按一定的比例创建一个新的位图)<br>
     * 方 法 名：createScaledBitmap <br>
     * 创 建 人： <br>
     * 创建时间：2016-6-7 上午9:14:47 <br>
     * 修 改 人： <br>
     * 修改日期： <br>
     *
     * @param bitmap     用来构建子集的源位图
     * @param iconWidth  新位图期望的宽度
     * @param iconHeight 新位图期望的高度
     * @param filter     未知
     * @return Bitmap 一个新的按比例变化的位图。
     */
    public static Bitmap createScaledBitmap(Bitmap bitmap, int iconWidth, int iconHeight, boolean filter) {
        Bitmap bitmap2;
        try {
            bitmap2 = Bitmap.createScaledBitmap(bitmap, iconWidth, iconHeight, filter);
        } catch (OutOfMemoryError localOutOfMemoryError) {
            gc();
            bitmap2 = Bitmap.createScaledBitmap(bitmap, iconWidth, iconHeight, filter);
        }
        return bitmap2;
    }

}
