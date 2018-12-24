package com.example.administrator.summarylearning.refreshandloadactivity.headlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import java.text.SimpleDateFormat;

/**
 * @Author
 * @Time 2018/12/10 11:29
 * @Describe
 * @Modify
 */
public class MyHeader extends LinearLayout implements RefreshHeader {

    private ImageView imageView;
    private TextView timeText;       //时间
    private TextView content;       //内容
    private long perRefreshTime;
    private SimpleDateFormat simpleDateFormat;



    public MyHeader(Context context) {
        super(context);
        initView(context);
    }

    public MyHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);

    }

    public MyHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }

    public void initView(Context context){
        LayoutInflater.from(getContext()).inflate(R.layout.view_header_normal, this, true);
        imageView = findViewById(R.id.iv_pic);
        content  =findViewById(R.id.tv_content);
        timeText = findViewById(R.id.tv_time);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");



    }

    @NonNull
    @Override
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_animation);
        imageView.startAnimation(animation);
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        imageView.clearAnimation();
        if(success){
            content.setText("刷新完成");
        }else {
            content.setText("刷新失败");
        }
        return 500;//延迟500毫秒再弹回
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
                imageView.clearAnimation();
                break;
            case PullDownToRefresh:
                String timeStr = simpleDateFormat.format(perRefreshTime == 0 ? System.currentTimeMillis() : perRefreshTime);
                timeText.setText(timeStr);
                content.setText("下拉可以刷新");//1*小力下拉
                break;
            case ReleaseToRefresh:
                content.setText("释放立即刷新");//*2大力往下拉
                break;
            case RefreshReleased:
                content.setText("正在刷新");   //*3刚开始正在刷新
                break;
            case Refreshing:
                content.setText("正在刷新");    //*4后开始刷新
                perRefreshTime = System.currentTimeMillis();
                break;
            case Loading:                             //上拉加载直接让其回去，再次下拉时出错，会显示这个
                content.setText("正在加载...");
                break;
        }
    }
}
