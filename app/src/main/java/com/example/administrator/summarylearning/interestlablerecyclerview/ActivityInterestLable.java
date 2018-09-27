package com.example.administrator.summarylearning.interestlablerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.interestlablerecyclerview.adapter.LinkLabelAdapter;
import com.example.administrator.summarylearning.interestlablerecyclerview.bean.Bean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 兴趣标签recyclerview实现       总结：就是使用多布局
 */
public class ActivityInterestLable extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<Bean> beanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_lable);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData(){

        Bean bean1 = new Bean(100, "标题一", 1, 0, false);
        Bean bean2 = new Bean(200, "内容一", 2, 1, true);
        Bean bean3 = new Bean(200, "内容二", 3, 1, false);
        Bean bean4 = new Bean(200, "内容三", 4, 1, true);
        Bean bean5 = new Bean(200, "内容四", 5, 1, false);
        Bean bean6 = new Bean(200, "内容五", 6, 1, false);
        Bean bean7 = new Bean(100, "标题二", 7, 0, false);
        Bean bean8 = new Bean(200, "内容六", 8, 7, true);

        beanList.add(bean1);                beanList.add(bean2);
        beanList.add(bean3);                beanList.add(bean4);
        beanList.add(bean5);                beanList.add(bean6);
        beanList.add(bean7);                beanList.add(bean8);


    }

    public void initView(){
        final GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {       //这个方法返回的是当前位置的 item 跨度大小。
            @Override
            public int getSpanSize(int position) {                          //spanCount除以getSpanSize是每行的item个数  通过设置getSpanSize的返回值来设定每行的item个数
                int type = recyclerView.getAdapter().getItemViewType(position);//获得返回值
                if (type == 100) {       //标题类型
                    return mLayoutManager.getSpanCount();       //1个item
                } else {                //标签类型
                    return 1;                                   //4个item
                }
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);

        LinkLabelAdapter linkLabelAdapter = new LinkLabelAdapter(getApplicationContext(), beanList);
        recyclerView.setAdapter(linkLabelAdapter);



    }
}
