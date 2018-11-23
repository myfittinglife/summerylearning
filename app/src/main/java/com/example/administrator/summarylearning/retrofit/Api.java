package com.example.administrator.summarylearning.retrofit;

import android.provider.ContactsContract;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @Author
 * @Time 2018/11/23 10:21
 * @Describe
 * @Modify
 */
public interface Api {


    /**
     * @subscribe   参数在url问号之后
     * @param       user_id
     * @return      {"Time":"2018/11/23","author":"作者123","content":"内容一","title":"标题一"}
     * url=http://mock-api.com/2vKVbXK8.mock/       info?id=usr_id
     */
    @GET("info")
    Call<DataBean>getUserInfo(@Query("id") int user_id);


    /**
     *@subscribe        多个参数的访问，querymap不能注解实体类
     * @param params    多个参数
     * @return
     * url=http://mock-api.com/2vKVbXK8.mock/       info?id=123&title=hashmap
     */
    @GET("info")
    Call<DataBean> getUserInfo(@QueryMap Map<String,String> params);


    /**
     *这种方法还未实现，不知道咋弄？？？？？？？？？？？？？？？？？？？
     * @param user_id
     * @param params
     * @return
     * url=http://mock-api.com/2vKVbXK8.mock/info?newsid={资讯id}&type={类型}
     */
    @GET("info")
    Call<DataBean> getUsrInfo(@Query("newsid") String user_id, @QueryMap Map<String, String> params);



    /**
     * @subscribe   可变路径的Get请求
     * @param id    url中的可变参数
     * @return      {"Time":"2018/11/23","author":"作者123","content":"内容一","title":"标题一"}
     * url=http://mock-api.com/2vKVbXK8.mock/id
     */
    @GET("{id}")
    Call<DataBean> getBlog(@Path("id") int id);


    /**
     * @subscribe 添加头部字段    (和api的头部字段不同也可以返回)
     * @return
     */
    @Headers("version:1.1")
    @GET("info")
    Call<DataBean> getHeadBlog();

    /**
     * 添加多个都不字段
     * @return
     */
    @Headers({
            "version:1.2",
            "type:android"
    })
    @GET("info")
    Call<DataBean> getHeadBlog2();


    /**
     * Post方式   没搞懂？？？？？？？？？？？？？？？？？？
     */
    @POST("info/postdata")
    Call<StatusBean> postBlog(@Body DataBean dataBean);




}
