package com.example.administrator.summarylearning.recyclerview.adapter;


import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.recyclerview.bean.MultiBaseBean;

import java.util.List;

/**
 * @Author        LD
 * @Time          2018/11/20 10:48
 * @Describe      list选择item,BaseQuickAdapter使用
 * @Modify
 */
public class MultiBasicQuickAdapter extends BaseQuickAdapter<MultiBaseBean,BaseViewHolder> {

    private List<MultiBaseBean> choosedList;


    public MultiBasicQuickAdapter(int layoutResId,  List<MultiBaseBean> data) {
        super(layoutResId, data);
        this.choosedList = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiBaseBean item) {
//        reViewRadioButton(helper, item);
        helper.setText(R.id.tv_name, item.getName());
//        helper.getView(R.id.rb_choice).setClickable(false);
        ((RadioButton) helper.getView(R.id.rb_choice)).setChecked(item.isChoose());

        Glide.with(mContext).load(item.getIconUrl()).into((ImageView) helper.getView(R.id.iv_icon));

    }
//    /**
//     * 回显
//     * @param helper
//     * @param item
//     */
//    private void reViewRadioButton(BaseViewHolder helper, MultiBaseBean item) {
//        for (MultiBaseBean listBean : choosedList) {
//            if (item.getName().equals(listBean.getName())){
//                ((RadioButton) helper.getView(R.id.rb_choice)).setChecked(true);
//            }
//        }
//    }
}
