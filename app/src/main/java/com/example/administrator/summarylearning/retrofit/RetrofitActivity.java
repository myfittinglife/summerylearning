package com.example.administrator.summarylearning.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author LD
 * @Time 2018/11/23
 * @Describe retrofit（入门使用）
 * @Modify
 */
public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "1123ceshi";
    @BindView(R.id.tv_content)
    TextView tvContent;
    private Api api;
    private String baseUrl = "http://mock-api.com/2vKVbXK8.mock/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

//        Gson gson = new Gson();
//        DataBean bean = new DataBean("标题一","作者123","2018/11/23","内容一");
//        String s = gson.toJson(bean);
//        Log.i(TAG, "json数据为: "+s);
//--------------------------------------------------------------------------------------------------
//        getCommenGson(456);
//        queryCommen(456);
//        queryMap1();
//        queryMap2();

//        headFun1();
//        headFun2();

        postFun();






    }

    /**
     * 提交内容（未成功）
     */
    private void postFun() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.postBlog(new DataBean("标题","作者","时间","内容"))
                .enqueue(new Callback<StatusBean>() {
                    @Override
                    public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                        tvContent.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<StatusBean> call, Throwable t) {
                        tvContent.setText(t.hashCode()+"\n"+t.getLocalizedMessage()+"\n"+t.getMessage()+t.getCause());
                    }
                });
    }

    /**
     * 添加头部字段
     */
    private void headFun1() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getHeadBlog().enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                tvContent.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                tvContent.setText(t.getLocalizedMessage());
            }
        });
    }


    /**
     * 添加多个头部字段
     */
    private void headFun2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getHeadBlog2().enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                tvContent.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                tvContent.setText(t.getLocalizedMessage());
            }
        });
    }

    /**
     * 普通带路径可更换id的Get请求获取数据
     * url=http://mock-api.com/2vKVbXK8.mock/   id
     * 123/456测试
     */
    private void getCommenGson(int id) {
        //创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)         //baseUrl中的path必须以/结束
                .addConverterFactory(GsonConverterFactory.create())         //自动将json字符串转化为相对应的类对象，字符串中若出现字段缺失或为null情况，会自动补充'null'
                .build();

        //创建代理对象
        api = retrofit.create(Api.class);
        api.getBlog(id).enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                Log.i(TAG, "返回的数据为: " + "\n" + response.body().toString());
                tvContent.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                t.printStackTrace();
                Log.i(TAG, "onFailure: 访问发生错误"+t);
                tvContent.setText("访问错误");

            }
        });
    }

    /**
     * 普通的Query请求
     * url=http://mock-api.com/2vKVbXK8.mock/   info?id=usr_id
     * 123/456测试
     */
    private void queryCommen(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        api.getUserInfo(id).enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                Log.i(TAG, "返回的数据为: " + "\n" + response.body().toString());
                tvContent.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                Log.i(TAG, "onFailure: 访问发生错误"+t);
                tvContent.setText("访问错误");
            }
        });
    }

    private void queryMap1() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);

        Map<String, String> map = new HashMap<>();
        map.put("id", "123");
        map.put("title", "hashmap");

        api.getUserInfo(map).enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                Log.i(TAG, "返回的数据为: " + "\n" + response.body().toString());
                tvContent.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                Log.i(TAG, "onFailure: 访问发生错误"+t);
                tvContent.setText("访问错误");
            }
        });
    }

    /**
     * 还未实现
     */
    private void queryMap2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);

        Map<String, String> map = new HashMap<>();
        map.put("id", "123");
        map.put("id", "456");
        map.put("title", "hashmap");
        map.put("title", "hashtable");

        api.getUserInfo(map).enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                Log.i(TAG, "返回的数据为: " + "\n" + response.body().toString());
                tvContent.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                Log.i(TAG, "onFailure: 访问发生错误"+t);
                tvContent.setText("访问错误");
            }
        });
    }



}
