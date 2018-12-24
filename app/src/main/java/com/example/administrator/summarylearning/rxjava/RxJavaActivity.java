package com.example.administrator.summarylearning.rxjava;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.BuildConfig;
import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.rxjava.bean.TheDataBean;
import com.example.administrator.summarylearning.rxjava.bean.TheDataBean2;
import com.example.administrator.summarylearning.rxjava.bean.ZipDataBean1;
import com.example.administrator.summarylearning.rxjava.bean.ZipDataBean2;
import com.example.administrator.summarylearning.rxjava.bean.ZipDataBeanAll;
import com.example.administrator.summarylearning.rxjava.text.RequestApi;
import com.example.administrator.summarylearning.rxjava.textrequest.Api;
import com.example.administrator.summarylearning.rxjava.textrequest.LoginRequest;
import com.example.administrator.summarylearning.rxjava.textrequest.LoginResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author LD
 * @Time 2018/11/21
 * @Describe RxJava使用
 * @Modify
 */
public class RxJavaActivity extends AppCompatActivity {


    private static final String TAG = "1122ceshi";
    @BindView(R.id.btn_common)
    Button btnCommon;
    @BindView(R.id.btn_chain)
    Button btnChain;
    @BindView(R.id.btn_coumser)
    Button btnCoumser;
    @BindView(R.id.btn_dispose)
    Button btnDispose;
    @BindView(R.id.btn_switchthread)
    Button btnSwitchthread;
    @BindView(R.id.btn_rxjava_retrofit)
    Button btnRxjavaRetrofit;
    @BindView(R.id.btn_getinfo)
    Button btnGetinfo;
    @BindView(R.id.btn_zip)
    Button btnZip;
    @BindView(R.id.btn_net_zip)
    Button btnNetZip;
    @BindView(R.id.btn_net_zip_fail)
    Button btnNetZipFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);
        //普通方式
//        fun1();

        //链式调用
//        chainFun();

        //上下游默认在同一个线程
//        consumerFun();

        //线程转化，被观察者Observable在子线程，Observable在主线程
//        turnThreadFun();

        //dispose()方法的使用
//        disposeFun();

        //下游线程切换

        //下游切换线程
//        switchThread();


//        observable.subscribeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG, "After observeOn(mainThread), current thread is: " + Thread.currentThread().getName());
//                    }
//                })
//                .observeOn(Schedulers.io())
//                .doOnNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
//                    }
//                })
//                .subscribe(consumer);


    }


    //*----------------------------------------------------------------------------------------------------------

    /**
     * 普通方式的建立连接
     */
    private void fun1() {
        //创建上游Observable      被观察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        //创建一个下游            观察者
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        };

        //建立连接
        observable.subscribe(observer);
    }

    /**
     * 链式调用
     */
    private void chainFun() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: " + integer);
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


    /**
     * 上下游默认在同一个线程
     */
    private void consumerFun() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.i(TAG, "Observable thread is :" + Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "Observer thread is:" + Thread.currentThread().getName());
                Log.i(TAG, "accept: " + integer);
            }
        };

        observable.subscribe(consumer);
    }

    /**
     * 线程转化，被观察者Observable在子线程，Observable在主线程
     */
    private void turnThreadFun() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.i(TAG, "Observable thread is :" + Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "Observer thread is:" + Thread.currentThread().getName());
                Log.i(TAG, "accept: " + integer);
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    /**
     * dispose()方法的使用
     */
    private void disposeFun() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.i(TAG, "subscribe: emit 1");
                emitter.onNext(1);
                Log.i(TAG, "subscribe: emit 2");
                emitter.onNext(2);
                Log.i(TAG, "subscribe: emit 3");
                emitter.onNext(3);
                Log.i(TAG, "subscribe: emit 4");
                emitter.onNext(4);

            }
        }).subscribe(new Observer<Integer>() {

            /**
             * 切断器，切断上下游，使得下游接受不到事件
             */
            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: " + integer);
                i++;
                if (i == 2) {
                    Log.i(TAG, "onNext: dispose");
                    //阻断
                    mDisposable.dispose();
                    Log.i(TAG, "onNext: " + mDisposable.isDisposed());
                }
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

    /**
     * 主要看下游线程切换的变化过程，每调用一次observeOn() 线程便会切换一次
     */
    private void switchThread() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.i(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.i(TAG, "emit 1");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.i(TAG, "onNext: " + integer);
            }
        };

//        observable.subscribeOn(Schedulers.newThread())              //指定上游事件（只有第一次有效）
//                .observeOn(AndroidSchedulers.mainThread())          //指定下游事件（没调用一次下游线程就切换一次）
//                .subscribe(consumer);

//        //上游切换观察变化，只有一次有效
//        observable.subscribeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(consumer);

//        //下游切换观察变化
//        observable.subscribeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.io())                         //会切换到io线程中
//                .subscribe(consumer);

        //下游切换观察变化
        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())                           //上游不管怎么切换，只以第一次的为准
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {                                                 //在上一步之后执行的操作
                    @Override
                    public void accept(Integer integer) throws Exception {      //先执行doOnNext，然后才执行consumer中的accept
                        Log.i(TAG, "After observeOn(mainThread), current thread is: " + Thread.currentThread().getName());

                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
                    }
                })
                .subscribe(consumer);
    }

    //*-----------------------网络请求--------------------------------
    private static Retrofit create() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        String url = "";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    private Disposable disposable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private void startRequest() {
        Retrofit retrofit = create();
        Api api = retrofit.create(Api.class);
        LoginRequest loginRequest = new LoginRequest();

        api.login(loginRequest)
                .subscribeOn(Schedulers.io())                   //IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())      //主线程中处理请求
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: 登录失败");
                    }

                    @Override
                    public void onComplete() {
                        //进行登录的判断
                        Log.i(TAG, "onComplete: 登录成功");
                    }
                });
    }


    //*---------------------Rxjava与Retroift连用,不同就是多添了一个addCallAdapterFactory-----------------------------
    private void fun() {
        String url = "http://mock-api.com/2vKVbXK8.mock/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        requestApi.getInfo(123)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<TheDataBean>() {
                    @Override
                    public void accept(TheDataBean theDataBean) throws Exception {      //再执行②
                        //根据响应的结果来做一些操作
                        Log.i(TAG, "accept: 接收到的数据" + theDataBean.toString());

                    }
                })
                .observeOn(Schedulers.io())
//                .flatMap(new Function<TheDataBean, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(TheDataBean theDataBean) throws Exception {
//                        return null;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TheDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {                                                             //先执行①
                        Log.i(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(TheDataBean theDataBean) {                                                      //然后执行③
                        Log.i(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
                        Log.i(TAG, "onNext: 执行onNext" + theDataBean.toString());

                    }

                    @Override
                    public void onError(Throwable e) {                                                                  //
                        Log.i(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {                                                                          //最后执行
                        Log.i(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
                        Log.i(TAG, "onComplete: 获取成功");
                    }
                });


    }

    //连续请求
    private void fun2() {
        String url = "http://mock-api.com/2vKVbXK8.mock/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        final RequestApi requestApi = retrofit.create(RequestApi.class);
        requestApi.getInfo(123)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<TheDataBean>() {
                    @Override
                    public void accept(TheDataBean theDataBean) throws Exception {
                        //根据响应的结果来做一些操作
                        Log.i(TAG, "accept: 接收到的数据" + theDataBean.toString());

                    }
                })
                .observeOn(Schedulers.io())             //回到IO线程去发起请求
//                .flatMap(new Function<TheDataBean, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(TheDataBean theDataBean) throws Exception {
//                        return null;
//                    }
//                })
                .flatMap(new Function<TheDataBean, ObservableSource<TheDataBean>>() {
                    @Override
                    public ObservableSource<TheDataBean> apply(TheDataBean theDataBean) throws Exception {
                        return requestApi.getInfo(456);     //发起请求
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TheDataBean>() {
                    @Override
                    public void accept(TheDataBean o) throws Exception {
                        Log.i(TAG, "accept:第二次得到的内容 " + o.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });


    }

    //嵌套请求，根据上一个的请求结果来请求下一个数据
    private void fun3() {
        String url = "http://mock-api.com/2vKVbXK8.mock/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        final RequestApi requestApi = retrofit.create(RequestApi.class);

        requestApi.getInfo(123)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<TheDataBean>() {
                    @Override
                    public void accept(TheDataBean theDataBean) throws Exception {
                        Log.i(TAG, "accept: 第一次请求结束");
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<TheDataBean, ObservableSource<TheDataBean2>>() {
                    @Override
                    public ObservableSource<TheDataBean2> apply(TheDataBean theDataBean) throws Exception {
                        //将网络请求1转化成网络请求2
                        Log.i(TAG, "这里是什么" + theDataBean.toString());
                        return requestApi.getSecondInfo(Integer.valueOf(theDataBean.getName()));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TheDataBean2>() {
                    @Override
                    public void accept(TheDataBean2 theDataBean2) throws Exception {
                        Log.i(TAG, "accept: 第二次请求结束" + theDataBean2.toString());

                    }
                });

    }

    //Zip函数使用（使用场景：一个界面需要展示用户的一些信息，而这些信息分别要从两个服务器接口中获取，只有当两个都获取到了只有才能进行展示）
    private void zipFun() {
        //上游1
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.i(TAG, "subscribe: emit 1");
                emitter.onNext(1);
//                Thread.sleep(1000);
                SystemClock.sleep(1000);
                Log.i(TAG, "subscribe: emit 2");
                emitter.onNext(2);
                SystemClock.sleep(1000);
                Log.i(TAG, "subscribe: emit 3");
                emitter.onNext(3);
                SystemClock.sleep(1000);
                Log.i(TAG, "subscribe: emit 4");
                emitter.onNext(4);
                SystemClock.sleep(1000);
//                Log.i(TAG, "onComplete1: ");
//                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());    //让两个水管分别位于不同的线程
        //上游2
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.i(TAG, "subscribe: emit A");
                emitter.onNext("A");
                SystemClock.sleep(1000);
                Log.i(TAG, "subscribe: emit B");
                emitter.onNext("B");
                SystemClock.sleep(1000);
                Log.i(TAG, "subscribe: emit C");
                emitter.onNext("C");
                SystemClock.sleep(1000);
                Log.i(TAG, "onComplete2: ");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());    //让两个水管分别位于不同的线程
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete下游: ");
            }
        });


    }

    //Zip函数网络请求下使用(正常情况下，两个observable都获取到了数据)
    private void zipWithNetFun() {
        String url = "http://mock-api.com/2vKVbXK8.mock/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RequestApi requestApi = retrofit.create(RequestApi.class);

        Observable<ZipDataBean1> observable1 = requestApi.getZipInfo1().subscribeOn(Schedulers.io());
        Observable<ZipDataBean2> observable2 = requestApi.getZipInfo2().subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<ZipDataBean1, ZipDataBean2, ZipDataBeanAll>() {
            @Override
            public ZipDataBeanAll apply(ZipDataBean1 zipDataBean1, ZipDataBean2 zipDataBean2) throws Exception {
                return new ZipDataBeanAll(zipDataBean1, zipDataBean2);
            }
        }).subscribe(new Consumer<ZipDataBeanAll>() {
            @Override
            public void accept(ZipDataBeanAll zipDataBeanAll) throws Exception {
                if (zipDataBeanAll != null) {
                    Log.i(TAG, "合并的内容为: " + zipDataBeanAll.show());

                }
            }
        });


    }

    //Zip函数网络请求下使用(非正常情况下，一个Observable未获取到或两个都未获取到)
    private void zipWithNetFun2() {
        String url = "http://mock-api.com";//故意写错"http://mock-api.com/2vKVbXK8.mock/"
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RequestApi requestApi = retrofit.create(RequestApi.class);

        Observable<ZipDataBean1> observable1 = requestApi.getZipInfo1().subscribeOn(Schedulers.io());
        Observable<ZipDataBean2> observable2 = requestApi.getZipInfo2().subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<ZipDataBean1, ZipDataBean2, ZipDataBeanAll>() {
            @Override
            public ZipDataBeanAll apply(ZipDataBean1 zipDataBean1, ZipDataBean2 zipDataBean2) throws Exception {
                return new ZipDataBeanAll(zipDataBean1, zipDataBean2);
            }
        }).subscribe(new Observer<ZipDataBeanAll>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ZipDataBeanAll zipDataBeanAll) {
                Log.i(TAG, "合并的内容为: " + zipDataBeanAll.show());
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: 发生错误");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: 完成");
            }
        });
    }


    @OnClick({R.id.btn_common, R.id.btn_chain, R.id.btn_coumser, R.id.btn_dispose, R.id.btn_switchthread, R.id.btn_rxjava_retrofit, R.id.btn_getinfo, R.id.btn_zip, R.id.btn_net_zip,
    R.id.btn_net_zip_fail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_common:       //普通使用
                fun1();
                break;
            case R.id.btn_chain:        //链式调用
                chainFun();
                break;
            case R.id.btn_coumser:      //上下游在同一线程  consumer使用
                consumerFun();
                break;
            case R.id.btn_dispose:      //dispose切断上下游
                disposeFun();
                break;
            case R.id.btn_switchthread: //切换线程
                switchThread();
                break;
            case R.id.btn_rxjava_retrofit:      //rxjava和retrofit连用
                fun();
                break;
            case R.id.btn_getinfo:      //嵌套请求
                fun3();
                break;
            case R.id.btn_zip:          //zip操作符使用
                zipFun();
                break;
            case R.id.btn_net_zip:   //Zip网络操作
                zipWithNetFun();
                break;
            case R.id.btn_net_zip_fail:     //Zip网络操作失败
                zipWithNetFun2();
                break;
            default:
                break;
        }
    }
}
