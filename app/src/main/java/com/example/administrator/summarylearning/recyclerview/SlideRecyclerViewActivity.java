package com.example.administrator.summarylearning.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.recyclerview.adapter.SlideBasicAdapter;
import com.example.administrator.summarylearning.recyclerview.bean.BasicBean;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
*  @Author          LD
*  @Time            2018/11/20
*  @Describe        带侧滑删除的recyclerview
*  @Modify
*/
public class SlideRecyclerViewActivity extends AppCompatActivity implements SlideBasicAdapter.onClickDelete {


    @BindView(R.id.my_recyclerview)        //RecyclerView
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<BasicBean> myDataList = new ArrayList<>();
    private List<String> urls = new ArrayList<>();
    SlideBasicAdapter slideBasicAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_recycler_view);
        ButterKnife.bind(this);
        //Toolbar类要引入import android.support.v7.widget.Toolbar;
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"点击了菜单栏",Toast.LENGTH_SHORT).show();
//            }
//        });

        initData();
    }

    //数据初始化
    public void initData(){
        //竹
        urls.add("https://y.gtimg.cn/music/photo_new/T002R300x300M0000030BjyC0St8nf.jpg?max_age=2592000");
        //Just For Star洛杉矶音乐分享会
        urls.add("https://y.gtimg.cn/music/photo_new/T002R300x300M0000034bzu226F71i.jpg?max_age=2592000");
        //这就是爱
        urls.add("https://y.gtimg.cn/music/photo_new/T002R300x300M000000ggpQG3UWXX4.jpg?max_age=2592000");
        //未LIVE
        urls.add("https://y.gtimg.cn/music/photo_new/T002R300x300M000002JFTnH3VpOQt.jpg?max_age=2592000");
        //爱不解释
        urls.add("https://y.gtimg.cn/music/photo_new/T002R300x300M0000005Pjve2W3T4F.jpg?max_age=2592000");
        //明天过后
        urls.add("https://y.gtimg.cn/music/photo_new/T002R300x300M000003ymSNo20qzJS.jpg?max_age=2592000");
        //我想
        urls.add("https://y.gtimg.cn/music/photo_new/T002R300x300M000000p6Rug21Xg7d.jpg?max_age=2592000");
        //那些和我们打过招呼的爱情
        urls.add("https://y.gtimg.cn/music/photo_new/T002R300x300M000004RUizj2sJQz3.jpg?max_age=2592000");

        myDataList.add(new BasicBean(urls.get(0),"竹","张杰","10k","10k"));
        myDataList.add(new BasicBean(urls.get(1),"Just For Star洛杉矶音乐分享会","张杰","10k","10k"));
        myDataList.add(new BasicBean(urls.get(2),"这就是爱","张杰","10k","10k"));
        myDataList.add(new BasicBean(urls.get(3),"未LIVE","张杰","10k","10k"));
        myDataList.add(new BasicBean(urls.get(4),"爱不解释","张杰","10k","10k"));
        myDataList.add(new BasicBean(urls.get(5),"明天过后","张杰","10k","10k"));
        myDataList.add(new BasicBean(urls.get(6),"我想","张杰","10k","10k"));
        myDataList.add(new BasicBean(urls.get(7),"那些和我们打过招呼的爱情","张杰","10k","10k"));

//        SlideBasicAdapter slideBasicAdapter = new SlideBasicAdapter(getApplicationContext(),myDataList,this);
        slideBasicAdapter = new SlideBasicAdapter(getApplicationContext(),myDataList,this);
        recyclerView.setAdapter(slideBasicAdapter);
        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LinearLayoutManager);
        //设置各个item间的间隔线
        recyclerView.addItemDecoration(new MyItemDecoration(2));


//        谷歌自带的滑动删除滑动了就删除了，不能添加按钮来点击删除，需要自己实现ItemTouchHelper，不用自带的
//        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallBack(slideBasicAdapter);
//        ItemTouchHelper helper = new ItemTouchHelper(callback);
//        helper.attachToRecyclerView(recyclerView);
//
//        PlusItemSlideCallback callback = new PlusItemSlideCallback(WItemTouchHelperPlus.SLIDE_ITEM_TYPE_SLIDECONTAINER);
//        WItemTouchHelperPlus extension = new WItemTouchHelperPlus(callback);
//        extension.attachToRecyclerView(recyclerView);



    }

    //删除item
    @Override
    public void onDelete(int positon) {
        myDataList.remove(positon);
        slideBasicAdapter.notifyDataSetChanged();
    }
}
