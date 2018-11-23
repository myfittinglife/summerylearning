package com.example.administrator.summarylearning.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.summarylearning.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
*  @Author      LD
*  @Time        2018/11/21
*  @Describe    RxJava使用
*  @Modify
*/
public class RxJavaActivity extends AppCompatActivity {


    private static final String TAG = "1122ceshi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
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
                Log.i(TAG, "onNext: "+integer);
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
                Log.i(TAG, "onNext: "+integer);
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
                Log.i(TAG, "Observable thread is :"+Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "Observer thread is:"+Thread.currentThread().getName());
                Log.i(TAG, "accept: "+integer);
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
                Log.i(TAG, "Observable thread is :"+Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "Observer thread is:"+Thread.currentThread().getName());
                Log.i(TAG, "accept: "+integer);
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
                Log.i(TAG, "onNext: "+integer);
                i++;
                if(i==2){
                    Log.i(TAG, "onNext: dispose");
                    //阻断
                    mDisposable.dispose();
                    Log.i(TAG, "onNext: "+mDisposable.isDisposed());
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
        Observable<Integer> observable  = Observable.create(new ObservableOnSubscribe<Integer>() {
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
                Log.i(TAG, "Observer thread is :"+Thread.currentThread().getName());
                Log.i(TAG, "onNext: "+integer);
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {                                                 //在上一步之后执行的操作
                    @Override
                    public void accept(Integer integer) throws Exception {
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


}
