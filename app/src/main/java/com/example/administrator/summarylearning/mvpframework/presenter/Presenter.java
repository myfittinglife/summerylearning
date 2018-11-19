package com.example.administrator.summarylearning.mvpframework.presenter;

import com.example.administrator.summarylearning.mvpframework.Callback;
import com.example.administrator.summarylearning.mvpframework.model.HttpModel;
import com.example.administrator.summarylearning.mvpframework.view.MvpView;


/**
 * @Author
 * @Time        2018/11/19 17:19
 * @Describe    P层      调用M层具体的实现
 * @Modify
 */
public class Presenter {

    private HttpModel httpModel;
    private MvpView mvpView;

    public Presenter(final MvpView mvpView) {

        this.mvpView = mvpView;

        httpModel =new HttpModel(new Callback() {
            @Override
            public void onResult(String text) {
                if(Presenter.this.mvpView!=null){
                    Presenter.this.mvpView.updateTv(text);
                }
            }
        });

    }

    /**
     * 请求网络数据
     */
    public void requestMessage(){
        httpModel.requestMessage();
    }

    /**
     * 防止内存泄漏
     * 因为Activity被presenter引用，若退出Activity而model还在执行耗时操作，无法释放activity,会造成内存泄漏，解决：在activity的onDestroy()调用此方法
     */
    public void detachView(){
        mvpView = null;
        //取消handler任务，避免资源浪费
        httpModel.revokeMessage();

    }




}
