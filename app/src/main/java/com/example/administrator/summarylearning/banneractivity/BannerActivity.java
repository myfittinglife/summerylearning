package com.example.administrator.summarylearning.banneractivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.banneractivity.banner.BannerView;
import com.example.administrator.summarylearning.banneractivity.banner.DataBean;
import com.example.administrator.summarylearning.banneractivity.banner.holder.BannerViewHolder;
import com.example.administrator.summarylearning.banneractivity.banner.holder.BannerViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author
 * @Time            2018/12/5
 * @Describe        轮播图功能测试 使用时加上：mz_banner_effect_layout和mz_banner_normal_layout布局，attr文件，indicator_normal和indicator_selected文件
 * @Modify
 */
public class BannerActivity extends AppCompatActivity {


    @BindView(R.id.bannerviewpager)
    BannerView bannerviewpager;
    private List<DataBean> mockData = new ArrayList<>();
    private String url1 = "https://y.gtimg.cn/music/photo_new/T002R300x300M0000030BjyC0St8nf.jpg?max_age=2592000";//竹
    private String url2 = "https://y.gtimg.cn/music/photo_new/T002R300x300M0000005Pjve2W3T4F.jpg?max_age=2592000";//爱不解释
    private String url3 = "https://y.gtimg.cn/music/photo_new/T002R300x300M000004RUizj2sJQz3.jpg?max_age=2592000";//那些和我们打过招呼的爱情

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);
        initData();
    }
    public void initData(){
        mockData.add(new DataBean(url1,"图片一"));
        mockData.add(new DataBean(url2,"图片二"));
        mockData.add(new DataBean(url3,"图片三"));

       bannerviewpager.setPages(mockData, new BannerViewHolderCreator() {
           @Override
           public BannerViewHolder createViewHolder() {
               return new MyBannerViewHolder();
           }
       });

       bannerviewpager.setBannerPageClickListener(new BannerView.BannerPageClickListener() {
           @Override
           public void onPageClick(View view, int position) {
               Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();
           }
       });


    }
    public  static class MyBannerViewHolder implements BannerViewHolder<DataBean> {
        private ImageView mImageView;
        private TextView mTextView;

        @Override
        public View createView(Context context) {
            // 返回ViewPager 页面展示的布局
            View view = LayoutInflater.from(context).inflate(R.layout.common_item_viewpager,null);
            mImageView = (ImageView) view.findViewById(R.id.viewPager_item_image);
            mTextView = (TextView) view.findViewById(R.id.item_desc);
            return view;
        }

        @Override
        public void onBind(Context context, int positon, DataBean data) {
        // 数据绑定
            // 自己绑定数据，灵活度很大
            Glide.with(context).load(data.getImageResId()).into(mImageView);
            mTextView.setText(data.getDesc());
        }
    }
    //*--------


    @Override
    protected void onResume() {
        super.onResume();
        bannerviewpager.start();//开始轮播
    }

    @Override
    protected void onPause() {
        super.onPause();
        bannerviewpager.pause();//停止轮播
    }
}
