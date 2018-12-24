package com.example.administrator.summarylearning.rxjava.text;

import com.example.administrator.summarylearning.rxjava.bean.TheDataBean;
import com.example.administrator.summarylearning.rxjava.bean.TheDataBean2;
import com.example.administrator.summarylearning.rxjava.bean.ZipDataBean1;
import com.example.administrator.summarylearning.rxjava.bean.ZipDataBean2;

import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;


/**
 * @Author
 * @Time        2018/11/30 10:23
 * @Describe    Retrofit和Rxjava使用的api(与retrofit单独使用不同的就是将call改为Observable,然后retrofitnew)
 * @Modify
 */
public interface RequestApi {


    /**
     *
     * @param userid
     * @return
     */
    @GET("rxinfo")
    Observable<TheDataBean> getInfo(@Query("id") int userid);

    @GET("rxinfo")
    Observable<TheDataBean2> getSecondInfo(@Query("id")int id);

    @GET("zipinfo1")
    Observable<ZipDataBean1> getZipInfo1();
    @GET("zipinfo2")
    Observable<ZipDataBean2> getZipInfo2();

}
