package com.example.administrator.summarylearning.commonviewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.summarylearning.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author LD
 * @Time 2018/12/6
 * @Describe 打造通用的ViewPager
 * @Modify
 */
public class CommonViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.common_view_pager)
    CommonViewPager commonViewPager;
    private List<DataEntry> mockData = new ArrayList<>();
    private String url1 = "https://y.gtimg.cn/music/photo_new/T002R300x300M0000030BjyC0St8nf.jpg?max_age=2592000";//竹
    private String url2 = "https://y.gtimg.cn/music/photo_new/T002R300x300M0000005Pjve2W3T4F.jpg?max_age=2592000";//爱不解释
    private String url3 = "https://y.gtimg.cn/music/photo_new/T002R300x300M000004RUizj2sJQz3.jpg?max_age=2592000";//那些和我们打过招呼的爱情





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_view_pager);
        ButterKnife.bind(this);

        mockData.add(new DataEntry(url1,"图片一"));
        mockData.add(new DataEntry(url2,"图片二"));
        mockData.add(new DataEntry(url3,"图片三"));




        commonViewPager.setPages(mockData, new ViewPagerHolderCreator() {//传入数据和viewholder实例
            @Override
            public ViewPagerHolder createViewHolder() {
                //返回ViewPagerHolder
                return new ViewImageHolder();
            }
        });


        int currentItem = getStartSelectItem();
        commonViewPager.setCurrentItem(currentItem);
        commonViewPager.start();

    }
    private int getStartSelectItem(){
        // 我们设置当前选中的位置为Integer.MAX_VALUE / 2,这样开始就能往左滑动
        // 但是要保证这个值与getRealPosition 的 余数为0，因为要从第一页开始显示
        int currentItem = Integer.MAX_VALUE / 2;
        if(currentItem % getRealCount()  ==0 ){
            return currentItem;
        }
        // 直到找到从0开始的位置
        while (currentItem % getRealCount()  != 0){
            currentItem++;
        }
        return currentItem;
    }
    private int getRealCount(){
        return  mockData==null ? 0:mockData.size();
    }


//*----------------------------------------------------------------------------------

//*----------------------------------------------------------------------------------
    /**
     * 提供ViewPager展示的ViewHolder
     * <P>用于提供布局和绑定数据</P>
     */
    public static class ViewImageHolder implements ViewPagerHolder<DataEntry>{  //viewagerholdercreator继承自ViewPagerHolder
        private ImageView mImageView;
        private TextView mTextView;
        @Override
        public View CreateView(Context context) {
            // 返回ViewPager 页面展示的布局
            View view = LayoutInflater.from(context).inflate(R.layout.common_item_viewpager,null);
            mImageView = (ImageView) view.findViewById(R.id.viewPager_item_image);
            mTextView = (TextView) view.findViewById(R.id.item_desc);
            return view;
        }

        @Override
        public void onbind(Context context, int positon, DataEntry data) {
            // 数据绑定
            // 自己绑定数据，灵活度很大
//            mImageView.setImageResource(data.imageResId);
            Glide.with(context).load(data.getImageResId()).into(mImageView);
            mTextView.setText(data.desc);
        }
    }


}
