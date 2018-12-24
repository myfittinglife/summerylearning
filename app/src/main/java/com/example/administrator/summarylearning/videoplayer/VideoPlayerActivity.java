package com.example.administrator.summarylearning.videoplayer;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.summarylearning.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author      LD
 * @Time        2018/12/13
 * @Describe    视频播放页面
 * @Modify
 */
public class VideoPlayerActivity extends AppCompatActivity {

    @BindView(R.id.videoplayer)
    StandardGSYVideoPlayer videoplayer;
    private OrientationUtils orientationUtils;  //用于控制旋转的

    private String videoUrl = "http://mvvideo10.meitudata.com/57fa5f5c2a22b3457.mp4?k=c2186eee16eab1c826550d94f0a2d7dc&t=5c186721";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);

        initData();
    }
    public void initData(){
        videoplayer.setUp(videoUrl, true, "标题");
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.item_pic);
//        Glide.with(this).load("").into(imageView);
        videoplayer.setThumbImageView(imageView);

        //增加Title
        videoplayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoplayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this,videoplayer);

        videoplayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                orientationUtils.resolveByClick();//设置全屏按键功能，这里是使用选择屏幕而不是全屏
                videoplayer.startWindowFullscreen(VideoPlayerActivity.this, false, true);

            }
        });
        //是否可以滑动调整
        videoplayer.setIsTouchWiget(true);
        //设置返回键功能
        videoplayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //显示wifi确定框
        videoplayer.startPlayLogic();

    }

    @Override
    protected void onResume() {
        super.onResume();
        videoplayer.onVideoResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoplayer.onVideoPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if(orientationUtils!=null){
            orientationUtils.releaseListener();
        }
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if(orientationUtils.getScreenType()== ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        {
            videoplayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoplayer.setVideoAllCallBack(null);
        super.onBackPressed();

    }
}
