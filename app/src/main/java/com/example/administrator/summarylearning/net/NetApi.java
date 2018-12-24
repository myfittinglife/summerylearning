package com.example.administrator.summarylearning.net;

import com.example.administrator.summarylearning.net.bean.WXAccessTokenBean;
import com.example.administrator.summarylearning.retrofit.DataBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @Author
 * @Time            2018/12/11 14:55
 * @Describe        各种Api接口
 * @Modify
 */
public interface NetApi {

    @GET("access_token")
    Call<WXAccessTokenBean> getWXAccessTokenBean(@QueryMap Map<String, Object> params);


    @GET("access_token")
    Call<ResponseBody> getWXAccessTokenBean2(@QueryMap Map<String, Object> params);

    //*----测试
    @POST("portal/tender/info")
    Call<ResponseBody> getTextData();

    @POST("{id}")
    Call<ResponseBody> getCommonDate();



}
