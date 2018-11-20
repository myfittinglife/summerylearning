package com.example.administrator.summarylearning.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.recyclerview.adapter.MultiBasicQuickAdapter;
import com.example.administrator.summarylearning.recyclerview.bean.MultiBaseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author          LD
 * @Time            2018/11/20
 * @Describe        多选recyclerview,使用BaseQuickAdapter,     implementation 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.30'
 * @Modify
 */
public class MultiChoiceActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.btn_publish)
    Button btnPublish;

    private MultiBasicQuickAdapter multiBasicQuickAdapter;
    private List<MultiBaseBean> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choice_recyclerview);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {

        dataList = new ArrayList<>();
        dataList.add(new MultiBaseBean("http://a.hiphotos.baidu.com/baike/pic/item/64380cd7912397dd11d619e15e82b2b7d1a287ac.jpg", "张杰"));
        dataList.add(new MultiBaseBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543299582&di=19a3fad6065cc94cf73e0e58644c8405&imgtype=jpg&er=1&src=http%3A%2F%2Fimg1.c.yinyuetai.com%2Fimg1.c.yinyuetai.com%2Fartist%2Ffan%2F150805%2F0%2F-M-72687eb77b67119f69a7eb42a20ae603_0x0.jpg", "谢娜"));
        dataList.add(new MultiBaseBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542704805824&di=c45a5e44784da516b46edae7719dc56d&imgtype=0&src=http%3A%2F%2Fe0.ifengimg.com%2F07%2F2018%2F1117%2F69473F51168D90284559473715FBC380ED3FA4AB_size75_w845_h904.jpeg", "刘昊然"));
        dataList.add(new MultiBaseBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542705038769&di=f861987e500f1b1549a0dcf348ecec1f&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-01-19%2F5a614ad77b718.jpg", "易烊千玺"));
        dataList.get(0).setChoose(true);

        multiBasicQuickAdapter = new MultiBasicQuickAdapter(R.layout.item_recycle_multichoice, dataList);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(multiBasicQuickAdapter);
        multiBasicQuickAdapter.setOnItemClickListener(this);

    }

    @OnClick(R.id.btn_publish)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_publish:
                StringBuffer content= new StringBuffer();
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).isChoose()) {
                        content.append(dataList.get(i).getName() + "\t" + "位置：" + i + "\n");
                    }
                }
                Toast.makeText(getApplicationContext(), "已选择的人物为："+"\n"+content, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    //点击事件
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        RadioButton radioButton = view.findViewById(R.id.rb_choice);
        if (radioButton.isChecked()) {
            radioButton.setChecked(false);
            dataList.get(position).setChoose(false);
        } else {
            radioButton.setChecked(true);
            dataList.get(position).setChoose(true);
        }

    }


}
