package com.example.administrator.summarylearning.rxjava.textrequest;



import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * @Author
 * @Time 2018/11/22 11:58
 * @Describe
 * @Modify
 */
public interface Api {
    @GET
    Observable<LoginResponse> login(@Body LoginRequest request);

    @GET
    Observable<RegisterResponse> register(@Body RegisterRequest request);

//    HttpLoggingInterceptor    打印工具，日志拦截器
}
