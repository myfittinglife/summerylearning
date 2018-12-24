package com.example.administrator.summarylearning.pulldownmenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.summarylearning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author      LD
 * @Time        2018/12/12 14:18
 * @Describe    包含recyclerview的popwindow
 * @Modify
 */
public class ListPopwindow extends PopupWindow {

    private List<String> list = new ArrayList<>();
    private Activity context;
    private theSelectedListener theSelectedListener;
    private RecyclerView recyclerView;
    private PopupwindowAdapter myAdapter;


    public ListPopwindow(Activity context, final theSelectedListener selectedListener , final List<String> datalist) {
        super(context);
        this.context = context;
        this.theSelectedListener = selectedListener;
        this.list = datalist;

        //设置布局
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_popwindow, null);
        setContentView(view);
        recyclerView = view.findViewById(R.id.recyclerview);

        //设置数据
        myAdapter = new PopupwindowAdapter(R.layout.item_list_popwindow, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));//*不写这个会不显示
        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                theSelectedListener.setText2(list.get(position));
                dismiss();
                darkenBackground(1f);
            }
        });





        //设置宽高
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        //设置进出动画
//        setAnimationStyle(R.style.);

        //设置背景，只有设置了这个才可以点击外边和back消失,才能触发OnDismisslistener
        ColorDrawable dw = new ColorDrawable(0000000000);
        setBackgroundDrawable(dw);

        //设置可以获得焦点,这样其内的控件才可以获得点击事件，否则会被父View给拦截
        setFocusable(true);

        //设置点击外边可以消失
        setOutsideTouchable(true);

        //设置可以触摸
        setTouchable(true);

        //设置点击外边可以消失
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //判断是不是点击了外部
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    return true;
                }
                //不是点击外部
                return false;

            }
        });

        darkenBackground(0.9f);

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
                theSelectedListener.selectnull2();

            }
        });
    }

    /**
     * 改变背景颜色，主要是在PopupWindow弹出时背景变化，通过透明度设置
     */
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgcolor;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


    //用于更改外部框的内容
    public interface theSelectedListener {
        void setText2(String s);
        void selectnull2();
    }

}
