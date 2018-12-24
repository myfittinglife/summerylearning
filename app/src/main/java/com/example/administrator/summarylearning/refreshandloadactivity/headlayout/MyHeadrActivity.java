package com.example.administrator.summarylearning.refreshandloadactivity.headlayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.refreshandloadactivity.headlayout.apinet.ApiNet;
import com.example.administrator.summarylearning.refreshandloadactivity.headlayout.apinet.RefreshData;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author
 * @Time
 * @Describe 自定义的刷新头部的使用
 * @Modify
 */
public class MyHeadrActivity extends AppCompatActivity implements OnLoadMoreListener, OnRefreshListener {


    @BindView(R.id.btn_smartrefreshlayout)
    SmartRefreshLayout btnSmartrefreshlayout;
    @BindView(R.id.start_refresh_success)
    Button startRefreshSuccess;
    @BindView(R.id.start_refresh_fail)
    Button startRefreshFail;
    @BindView(R.id.tv_data)
    TextView tvData;


    private String baseUrl = "http://mock-api.com/2vKVbXK8.mock/";
    private ApiNet api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_header);
        ButterKnife.bind(this);
        btnSmartrefreshlayout.setOnRefreshListener(this);
        btnSmartrefreshlayout.setOnLoadMoreListener(this);
    }

//    @OnClick({R.id.stop_refresh,R.id.stop_load})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.stop_refresh:
//                btnSmartrefreshlayout.finishRefresh();
//                break;
//            case R.id.stop_load:
////                btnSmartrefreshlayout.finishLoadMore();
//                btnSmartrefreshlayout.finishLoadMore(true);
//
//                break;
//            default:
//                break;
//        }
//    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        getLoadData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {//里面执行刷新的操作
        getRefreshData();
    }


    @OnClick({R.id.start_refresh_success, R.id.start_refresh_fail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_refresh_success:

                break;
            case R.id.start_refresh_fail:
                break;
        }
    }

    //*获取刷新数据
    public void getRefreshData() {
        //创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)         //baseUrl中的path必须以/结束
                .addConverterFactory(GsonConverterFactory.create())         //自动将json字符串转化为相对应的类对象，字符串中若出现字段缺失或为null情况，会自动补充'null'
                .build();

        //创建代理对象
        api = retrofit.create(ApiNet.class);
        api.getRefreshData().enqueue(new Callback<RefreshData>() {
            @Override
            public void onResponse(Call<RefreshData> call, Response<RefreshData> response) {
                if (response.body() != null && response.body().isOk() == true) {
                    btnSmartrefreshlayout.finishRefresh();
                    String s = response.body().getContent();
                    Log.i("ceshi1210", "onResponse: " + s);
                    tvData.setText(s);
                    Log.i("ceshi1210", "刷新成功");
                } else {
                    btnSmartrefreshlayout.finishRefresh(false);
                    tvData.setText("刷新失败1");
                    Log.i("ceshi1210", "刷新失败1");

                }
            }

            @Override
            public void onFailure(Call<RefreshData> call, Throwable t) {
                btnSmartrefreshlayout.finishRefresh(false);
                tvData.setText("刷新失败2");
                Log.i("ceshi1210", "刷新失败2");

            }
        });
    }

    //*获取加载数据
    public void getLoadData() {
        //创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)         //baseUrl中的path必须以/结束
                .addConverterFactory(GsonConverterFactory.create())         //自动将json字符串转化为相对应的类对象，字符串中若出现字段缺失或为null情况，会自动补充'null'
                .build();

        //创建代理对象
        api = retrofit.create(ApiNet.class);
        api.getLoadData().enqueue(new Callback<RefreshData>() {
            @Override
            public void onResponse(Call<RefreshData> call, Response<RefreshData> response) {
                if (response.body() != null && response.body().isOk() == true) {
                    btnSmartrefreshlayout.finishLoadMore();
                    tvData.setText(response.body().getContent());

                } else {
                    btnSmartrefreshlayout.finishLoadMore(false);
                    tvData.setText("加载失败1");
                    Log.i("ceshi1210", "加载失败1");

                }
            }

            @Override
            public void onFailure(Call<RefreshData> call, Throwable t) {
                btnSmartrefreshlayout.finishLoadMore(false);
                tvData.setText("加载失败2");
                Log.i("ceshi1210", "加载失败2");


            }
        });
    }


}
