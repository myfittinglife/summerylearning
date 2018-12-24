package com.example.administrator.summarylearning.commonviewpager;

/**
 * @Author
 * @Time        2018/12/6 18:01
 * @Describe    创建ViewHolder生成器，用来生产各种ViewHolder，该类是一个泛型，但是必须是继承自ViewPagerHolder的子类
 * @Modify
 */
public interface ViewPagerHolderCreator<VH extends ViewPagerHolder> {

    /**
     * 创建ViewHolder，返回ViewHolder实例
     * @return
     */
    public VH createViewHolder();

}
