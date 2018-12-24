package com.example.administrator.summarylearning.refreshandloadactivity.headlayout.apinet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author
 * @Time 2018/12/10 15:42
 * @Describe 模拟刷新访问数据的接口
 * @Modify
 */
public interface ApiNet {

    @GET("refreshinfo")
    Call<RefreshData> getRefreshData();

    @GET("loadinfo")
    Call<RefreshData> getLoadData();

}
