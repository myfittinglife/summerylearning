package com.example.administrator.summarylearning.text;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.net.NetApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
*  @Author      LD
*  @Time
*  @Describe    测试类
*  @Modify
*/
public class TextActivity2 extends AppCompatActivity {

    private static final String TAG = "ceshi_1215TextActivity2";
    @BindView(R.id.btn_getinfo)
    Button btnGetinfo;
    @BindView(R.id.tv_content)
    TextView tvContent;
    String baseUrl = "http://192.168.1.22:8080/";
    String baseUrl2 = "http://mock-api.com/2vKVbXK8.mock/";
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.btn_getImage)
    Button btnGetImage;
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

    @OnClick({R.id.btn_getinfo,R.id.btn_getImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getinfo:
                initData();
                break;
            case R.id.btn_getImage:
                rxFun("https://y.gtimg.cn/music/photo_new/T002R300x300M000002JFTnH3VpOQt.jpg?max_age=2592000");
                break;

            default:
                break;
        }
    }

    public void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();
        netApi = retrofit.create(NetApi.class);
        netApi.getTextData().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    try {
                        String jsonString = new String(response.body().bytes());
                        Log.i(TAG, "onResponse: " + jsonString);
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

    public void rxFun(final String url) {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                Bitmap bitmap = getBitmap(url);
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
                bitmap.recycle();
                emitter.onNext(thumbBmp);
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Bitmap o) {
                        Log.i(TAG, "onNext: ");
                        imageview.setImageBitmap(o);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                });
    }

    public Bitmap getBitmap(String url) {
        URL htmlUrl = null;
        InputStream inStream = null;
        try {
            htmlUrl = new URL(url);
            URLConnection connection = htmlUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(inStream);

    }
}
