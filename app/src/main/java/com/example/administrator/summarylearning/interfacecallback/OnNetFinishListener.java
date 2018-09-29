package com.example.administrator.summarylearning.interfacecallback;

import com.example.administrator.summarylearning.okgo.bean.JsonBean1;

public interface OnNetFinishListener {
     void onError(JsonBean1 jsonBean1);
     void onSuccess(JsonBean1 jsonBean1);

}
