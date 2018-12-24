package com.example.administrator.summarylearning.text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.net.NetApi;
import com.example.administrator.summarylearning.refreshandloadactivity.headlayout.apinet.RefreshData;
import com.example.administrator.summarylearning.retrofit.Api;
import com.example.administrator.summarylearning.retrofit.DataBean;
import com.example.administrator.summarylearning.retrofit.StatusBean;
import com.example.administrator.summarylearning.rxjava.bean.ZipDataBean1;
import com.example.administrator.summarylearning.rxjava.bean.ZipDataBean2;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TextActivity2 extends AppCompatActivity {

    private static final String TAG = "ceshi_1215TextActivity2";
    @BindView(R.id.btn_getinfo)
    Button btnGetinfo;
    @BindView(R.id.tv_content)
    TextView tvContent;
    String baseUrl = "http://192.168.1.22:8080/";
    String baseUrl2 = "http://mock-api.com/2vKVbXK8.mock/";
    private NetApi netApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text2);
        ButterKnife.bind(this);
//        DataBean dataBean = new DataBean();
//        dataBean.setAuthor("作者");
//        dataBean.setContent("内容");
//        dataBean.setTime("时间");
//        dataBean.setTitle("标题");
//        Gson gson = new Gson();
//        String s = gson.toJson(dataBean);
//        Log.i(TAG, "json字符串为："+s);
//        ZipDataBean1 zipDataBean1 = new ZipDataBean1();
//        zipDataBean1.setName("李栋");
//        zipDataBean1.setUrl("https://www.");
//
//        Gson gson = new Gson();
//        String s1 = gson.toJson(zipDataBean1);
//
//        ZipDataBean2 zipDataBean2 = new ZipDataBean2();
//        zipDataBean2.setAge(18);
//        zipDataBean2.setUrl("baidu.com/");
//        String s2 = gson.toJson(zipDataBean2);
//        Log.i(TAG, "onCreate: " + "\n" + s1 + "\n" + s2);
//
//        ZipDataBean2 dataBean2 = gson.fromJson(s2, ZipDataBean2.class);
//
//        RefreshData refreshData = new RefreshData(true, "内容");
//        String refreshDataString = gson.toJson(refreshData);
//        Log.i("refreshdata", "刷新的数据" + refreshDataString);

    }

    @OnClick(R.id.btn_getinfo)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getinfo:
                initData();
                break;
            default:
                break;
        }
    }
    public void initData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();
        netApi = retrofit.create(NetApi.class);
        netApi.getTextData().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null){
                    try {
                        String jsonString = new String(response.body().bytes());
                        Log.i(TAG, "onResponse: "+jsonString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: 访问失败");
            }
        });
//        netApi.getTextData().enqueue(new Callback<DataBean>() {
//            @Override
//            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
//                if(response!=null){
//                    Log.i(TAG, "onResponse: "+response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DataBean> call, Throwable t) {
//
//            }
//        });
//*-----
//        netApi.getTextData().enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response != null) {
//                    ResponseBody a = response.body();
//                    Log.i(TAG, "onResponse: " + response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.i(TAG, "onFailure: "+t);
//            }
//        });
//        netApi.getTextData().enqueue(new Callback<R>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if(response!=null){
//                    Log.i(TAG, "onResponse: "+response);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.i(TAG, "onFailure: "+t);
//            }
//        });

    }
}
