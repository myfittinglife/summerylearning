package com.example.administrator.summarylearning.commonviewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.summarylearning.R;

import java.util.List;

/**
 * @Author
 * @Time            2018/12/6 18:03
 * @Describe        重写ViewPagerHolder的子类
 * 这个类比较重要，因为以前我们的布局提供和数据绑定都是在Adapter中的，因此现在我们就将这两项工作交给我们的ViewHolder。
 * CommonViewPagerAdapter 的构造方法需要展示的数据集合和ViewPagerHolderCreator 生成器。
 * @Modify
 */
public class CommonViewPagerAdapter<T> extends PagerAdapter {

    private List<T> mDatas;
    private ViewPagerHolderCreator mCreator;        //ViewHolder生成器
    private final int mLooperCountFactor = 500;


    public CommonViewPagerAdapter(List<T> mDatas, ViewPagerHolderCreator mCreator) {

        this.mDatas = mDatas;
        this.mCreator = mCreator;
    }
    @Override
    public int getCount() {
//        return mDatas == null?0:mDatas.size();
        return getRealCount() * mLooperCountFactor;
    }
    @Override
    public boolean isViewFromObject( View view,  Object object) {
        return view == object;
    }
    /**
     * 重点在这，不再是将布局写死，而是用接口提供的布局
     * 也不在这绑定数据，数据绑定交给Api调动者
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem( ViewGroup container, int position) {

        View view = getView(position,null,container);
        container.addView(view);
        return view;

    }

    public View getView(int position, View view, final ViewGroup container){

        final int realPosition = position % getRealCount();
        ViewPagerHolder holder = null;
        //*----
        holder = mCreator.createViewHolder();
        if(holder==null){
            throw new RuntimeException("can not return a null holder");
        }
        // create View
        View myView = holder.CreateView(container.getContext());
        if(mDatas!=null&&mDatas.size()>0){
            holder.onbind(container.getContext(),realPosition,mDatas.get(realPosition));
        }
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(container.getContext(),"点击",Toast.LENGTH_SHORT).show();
            }
        });
        return myView;
//        if(view==null){
//            //创建ViewHolder
//            holder=mCreator.createViewHolder();
//            view=holder.CreateView(container.getContext());
//            view.setTag(R.id.view_pager_item_tag,holder);//*在String文件夹内自定义的tag
//        }else {
//            holder = (BannerViewHolder) view.getTag(R.id.view_pager_item_tag);//*
//        }
//        if(holder!=null&&mDatas!=null&&mDatas.size()>0){
//            //数据绑定
////            holder.onbind(container.getContext(),position,mDatas.get(position));
//            holder.onbind(container.getContext(),realPosition,mDatas.get(realPosition));
//        }
//        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    //*----------------------
    /**
     * 获取真实的Count
     * @return
     */
    private int getRealCount(){
        return  mDatas==null ? 0:mDatas.size();
    }
}
