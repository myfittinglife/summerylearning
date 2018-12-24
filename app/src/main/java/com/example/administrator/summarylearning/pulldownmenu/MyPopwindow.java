package com.example.administrator.summarylearning.pulldownmenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;


/**
 * @Author      LD
 * @Time        2018/12/12 10:36
 * @Describe    普通的内容固定的自定义PopWindow
 * @Modify
 */
public class MyPopwindow extends PopupWindow implements View.OnClickListener {

    TextView tvAll;
    TextView tvOneweek;
    TextView tvOnemonth;
    TextView tvThreemonth;
    private Activity context;
    private selectedListener selectedListener;

    public MyPopwindow(Activity context, final selectedListener selectedListener) {
        super(context);
        this.context = context;
        this.selectedListener = selectedListener;

        //设置布局
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.common_popwindow, null);
        setContentView(view);

        tvAll = view.findViewById(R.id.tv_all);
        tvOneweek = view.findViewById(R.id.tv_oneweek);
        tvOnemonth = view.findViewById(R.id.tv_onemonth);
        tvThreemonth = view.findViewById(R.id.tv_threemonth);
        tvAll.setOnClickListener(this);
        tvOneweek.setOnClickListener(this);
        tvOnemonth.setOnClickListener(this);
        tvThreemonth.setOnClickListener(this);


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
                selectedListener.selectnull();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
                dismiss();
                darkenBackground(1f);
                selectedListener.setText("全部");
                break;
            case R.id.tv_oneweek:
                dismiss();
                darkenBackground(1f);
                selectedListener.setText("近一周");
                break;
            case R.id.tv_onemonth:
                dismiss();
                darkenBackground(1f);
                selectedListener.setText("近一个月");

                break;
            case R.id.tv_threemonth:
                dismiss();
                darkenBackground(1f);
                selectedListener.setText("近三个月");
                break;
            default:
                break;
        }
    }

    //用于更改外部框的内容
    public interface selectedListener {
        void setText(String s);
        void selectnull();
    }

//    @OnClick({R.id.tv_all, R.id.tv_oneweek, R.id.tv_onemonth, R.id.tv_threemonth})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_all:
//                Toast.makeText(context,"全部",Toast.LENGTH_SHORT).show();
//                dismiss();
//                darkenBackground(1f);
//                break;
//            case R.id.tv_oneweek:
//                Toast.makeText(context,"近一周",Toast.LENGTH_SHORT).show();
//                dismiss();
//                darkenBackground(1f);
//                break;
//            case R.id.tv_onemonth:
//                Toast.makeText(context,"近一个月",Toast.LENGTH_SHORT).show();
//                dismiss();
//                darkenBackground(1f);
//                break;
//            case R.id.tv_threemonth:
//                Toast.makeText(context,"近一个月",Toast.LENGTH_SHORT).show();
////                dismiss();
////                darkenBackground(1f);
//                break;
//        }
//    }
}
