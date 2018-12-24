package com.example.administrator.summarylearning.refreshandloadactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.refreshandloadactivity.headlayout.MyHeadrActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author
 * @Time
 * @Describe 下拉刷新上拉加载功能  https://github.com/scwang90/SmartRefreshLayout
 * @Modify
 */
public class RefreshAndLoadActivity extends AppCompatActivity implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.my_recyclerview)
    RecyclerView myRecyclerview;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.btn_stop_refresh_success)
    Button btnStopRefreshSuccess;
    @BindView(R.id.btn_stop_refresh_fail)
    Button btnStopRefreshFail;
    @BindView(R.id.btn_stop_load_success)
    Button btnStopLoadSuccess;
    @BindView(R.id.btn_stop_load_fail)
    Button btnStopLoadFail;
    @BindView(R.id.btn_myheader_activity)   //跳转至我的头部活动
            Button btnMyheaderActivity;
    @BindView(R.id.btn_system_activity)
    Button btnSystemActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_and_load);
        ButterKnife.bind(this);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                refreshLayout.finishRefresh(2000);
//            }
//        });
//
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                refreshLayout.finishLoadMore(2000);
//
//            }
//        });

    }


    @OnClick({R.id.btn_stop_refresh_success, R.id.btn_stop_refresh_fail, R.id.btn_stop_load_success, R.id.btn_stop_load_fail, R.id.btn_myheader_activity,R.id.btn_system_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_stop_refresh_success:         //成功刷新
                refreshLayout.finishRefresh(true);
                break;
            case R.id.btn_stop_refresh_fail:            //刷新失败
                refreshLayout.finishRefresh(false);
                break;
            case R.id.btn_stop_load_success:            //加载成功
                refreshLayout.finishLoadMore(true);
                break;
            case R.id.btn_stop_load_fail:               //加载失败
                refreshLayout.finishLoadMore(false);
                break;
            case R.id.btn_myheader_activity:            //跳转至我的头部活动
                startActivity(new Intent(this, MyHeadrActivity.class));
                break;
            case R.id.btn_system_activity:              //跳转至系统自带的活动
                startActivity(new Intent(this,SystemActivity.class));
                break;

            default:
                break;
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {   //刷新的监听
        //在此处调用刷新的操作，在刷新完成后根据结果情况来相应的对refreshLayout做停止刷新的操作
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {  //加载的监听
        //在此处调用加载的操作，在加载完成后根据结果情况来相应的对refreshLayout做停止加载的操作

    }
}
