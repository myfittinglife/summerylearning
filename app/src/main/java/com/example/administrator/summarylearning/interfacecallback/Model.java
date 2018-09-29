package com.example.administrator.summarylearning.interfacecallback;

import com.example.administrator.summarylearning.okgo.JsonCallBack;
import com.example.administrator.summarylearning.okgo.bean.JsonBean1;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class Model {
    private String url2 = "http://www.wanandroid.com/tools/mockapi/10592/json1";

    OnNetFinishListener onNetFinishListener;

    public Model(OnNetFinishListener onNetFinishListener) {
        this.onNetFinishListener = onNetFinishListener;
    }

    /*返回的内容
     {
    "msg": "验证码发送成功",
    "isError": false,
    "isOk": true,
    "message": null,
    "status": "300"*/
    public void getMessage(){
        OkGo.<JsonBean1>get(url2)
                .execute(new JsonCallBack<JsonBean1>() {
                    @Override
                    public void onSuccess(Response<JsonBean1> response) {               //UI线程中
                        if (response != null && response.body() != null) {
                            onNetFinishListener.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onError(Response<JsonBean1> response) {
                        onNetFinishListener.onError(response.body());
                    }
                });
    }
}
