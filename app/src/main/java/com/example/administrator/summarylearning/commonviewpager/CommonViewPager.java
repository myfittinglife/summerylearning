package com.example.administrator.summarylearning.commonviewpager;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.summarylearning.R;

import java.util.List;

/**
 * @Author
 * @Time        2018/12/7 18:03
 * @Describe    CommonViewPager 是对ViewPager的包装，提供了一些ViewPager的常用方法
 *  其中有一个非常重要的方法public void setPages(List<T> data, BannerViewHolderCreator creator),提供数据和ViewHolder。
 *  其他的基本上都是ViewPager的方法。也可以通过getViewPager 获取到ViewPager 再调用ViewPager的方法。
 * @Modify
 */
public class CommonViewPager <T> extends RelativeLayout {

    private ViewPager mViewpager;
    private CommonViewPagerAdapter mAdapter;
//    private CircleIndicatorView mCircleIndicatorView;     添加底部点


    //*------------
    private boolean mIsAutoPlay = true;// 是否自动播放
    private int mCurrentItem = 0;//当前位置
    private Handler mHandler = new Handler();
    private int mDelayedTime = 3000;// Banner 切换时间间隔
    private boolean mIsCanLoop = true;// 是否可以轮播图片






    //*------------


    public CommonViewPager(Context context) {
        super(context);
        init();
    }
    public CommonViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }
    public CommonViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CommonViewPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init(){

        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_viewpager, this, true);
        mViewpager = view.findViewById(R.id.the_common_view_pager);
//        mCircleIndicatorView = (CircleIndicatorView) view.findViewById(R.id.common_view_pager_indicator_view);
    }

    public void setPages(List<T>data, ViewPagerHolderCreator creator){
        mAdapter = new CommonViewPagerAdapter(data, creator);
        mViewpager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
//        mCircleIndicatorView.setUpWithViewPager(mViewPager);
    }
    public void setCurrentItem(int currentItem){
        mViewpager.setCurrentItem(currentItem);
    }
    public void setOffscreenPageLimit(int limit){
        mViewpager.setOffscreenPageLimit(limit);
    }
    /**
     * 设置切换动画，see {@link ViewPager#setPageTransformer(boolean, ViewPager.PageTransformer)}
     * @param reverseDrawingOrder
     * @param transformer
     */
    public void setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer){
        mViewpager.setPageTransformer(reverseDrawingOrder,transformer);
    }
    /**
     * see {@link ViewPager#setPageTransformer(boolean, ViewPager.PageTransformer)}
     * @param reverseDrawingOrder
     * @param transformer
     * @param pageLayerType
     */
    public void setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer,
                                   int pageLayerType) {
        mViewpager.setPageTransformer(reverseDrawingOrder,transformer,pageLayerType);
    }
    /**
     * see {@link ViewPager#addOnPageChangeListener(ViewPager.OnPageChangeListener)}
     * @param listener
     */
    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener){
        mViewpager.addOnPageChangeListener(listener);
    }
//    /**
//     * 设置是否显示Indicator
//     * @param visible
//     */
//    private void setIndicatorVisible(boolean visible){
//        if(visible){
//            mCircleIndicatorView.setVisibility(VISIBLE);
//        }else{
//            mCircleIndicatorView.setVisibility(GONE);
//        }
//
//    }

    public ViewPager getViewPager() {
        return mViewpager;
    }

    //*---------------
    private final Runnable mLoopRunnable = new Runnable() {
        @Override
        public void run() {
            if(mIsAutoPlay){
                mCurrentItem = mViewpager.getCurrentItem();
                mCurrentItem++;
                if(mCurrentItem == mAdapter.getCount() - 1){
                    mCurrentItem = 0;
                    mViewpager.setCurrentItem(mCurrentItem,false);
                    mHandler.postDelayed(this,mDelayedTime);
                }else{
                    mViewpager.setCurrentItem(mCurrentItem);
                    mHandler.postDelayed(this,mDelayedTime);
                }
            }else{
                mHandler.postDelayed(this,mDelayedTime);
            }
        }
    };


    /******************************************************************************************************/
    /**                             对外API                                                               **/
    /******************************************************************************************************/
    /**
     * 开始轮播
     * <p>应该确保在调用用了CommonViewPager的setPages()方法之后再调用这个方法开始轮播</p>
     */
    public void start(){
        // 如果Adapter为null, 说明还没有设置数据，这个时候不应该轮播Banner
        if(mAdapter== null){
            return;
        }
        if(mIsCanLoop){
            pause();
            mIsAutoPlay = true;
            mHandler.postDelayed(mLoopRunnable,mDelayedTime);
        }
    }

    /**
     * 停止轮播
     */
    public void pause(){
        mIsAutoPlay = false;
        mHandler.removeCallbacks(mLoopRunnable);
    }

    /**
     * 设置是否可以轮播
     * @param canLoop
     */
    public void setCanLoop(boolean canLoop){
        mIsCanLoop = canLoop;
        if(!canLoop){
            pause();
        }
    }
    /**
     * 设置BannerView 的切换时间间隔
     * @param delayedTime
     */
    public void setDelayedTime(int delayedTime) {
        mDelayedTime = delayedTime;
    }









}
