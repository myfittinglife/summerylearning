package com.example.administrator.summarylearning.banneractivity.banner.holder;

import android.content.Context;
import android.view.View;

/**
 * @Author
 * @Time        2018/12/6 17:56
 * @Describe    定义ViewHolder接口来提供布局和数据绑定，ViewPagerHolder接收一个泛型T，这是绑定数据要用到的实体类
 * @Modify
 */
public interface BannerViewHolder<T> {
    /**
     * 创建View，提供adapter布局
     * @param context
     * @return
     */
    View createView(Context context);


    /**
     * 绑定数据
     * @param context
     * @param positon
     * @param data
     */
    void onBind(Context context, int positon, T data);


}
