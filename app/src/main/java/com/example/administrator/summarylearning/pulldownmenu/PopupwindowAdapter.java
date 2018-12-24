package com.example.administrator.summarylearning.pulldownmenu;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.summarylearning.R;

import java.util.List;

/**
 * @Author      LD
 * @Time        2018/12/12 14:27
 * @Describe    popwindow中的list适配器
 * @Modify
 */
public class PopupwindowAdapter extends BaseQuickAdapter<String ,BaseViewHolder> {


    public PopupwindowAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item, item);

    }
}
