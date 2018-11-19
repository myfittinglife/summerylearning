package com.example.administrator.summarylearning.mvpframework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.mvpframework.presenter.Presenter;
import com.example.administrator.summarylearning.mvpframework.view.MvpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author LD
 * @Time 2018.11.19  17:13
 * @Describe MVP架构使用    https://mp.weixin.qq.com/s/Jrv6f_TU-LrzKm65wyF97w
 * @Modify
 */
public class MvpActivity extends AppCompatActivity implements MvpView {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_get_message)
    Button btnGetMessage;

    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);

        /**
         * 因为继承自MvpView,所以可以将该this传过去
         */
        presenter = new Presenter(this);
    }

    /**
     * 集成MvpView接口来实现其更新方法
     * @param text  更新的内容
     */
    @Override
    public void updateTv(String text) {
        tvContent.setText(text);
    }

    @OnClick(R.id.btn_get_message)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_message:
                presenter.requestMessage();

                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄漏
        Log.i("1119ceshi", "onDestroy: ");
        presenter.detachView();
    }
}
