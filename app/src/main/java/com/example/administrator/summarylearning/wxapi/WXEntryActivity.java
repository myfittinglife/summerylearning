package com.example.administrator.summarylearning.wxapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.summarylearning.Constant;
import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.net.NetApi;
import com.example.administrator.summarylearning.net.bean.WXAccessTokenBean;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
*  @Author      LD
*  @Time        2018.12.21
*  @Describe    使用微信分享、微信登录必须写入此类，并放置在名为wxapi的包内
*  @Modify
*/
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {


    private Context mContext;
    private IWXAPI wxapi;
    private static final String TAG = "WXEntryActivity_ceshi";
    private static final int RETURN_MSG_TYPE_LOGIN = 1; //登录
    private static final int RETURN_MSG_TYPE_SHARE = 2; //分享
    private String getAccessTokenUrl="https://api.weixin.qq.com/sns/oauth2/";
    private NetApi api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry2);
        mContext = this;

        wxapi = WXAPIFactory.createWXAPI(WXEntryActivity.this, Constant.WXAPPID, false);

        //这句没有写,是不能执行回调的方法的
        wxapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp baseResp) {
        int type = baseResp.getType(); //类型：分享还是登录
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //用户拒绝授权
                showToast(mContext, "拒绝授权微信登录");
                Log.i(TAG, "onResp: 拒绝授权微信登录");
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //用户取消
                String message = "";
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    message = "取消了微信登录";
                    Log.i(TAG, "onResp: 取消了微信登录");
                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    message = "取消了微信分享";
                    Log.i(TAG, "onResp: 取消了微信分享");
                }
                showToast(mContext, message);
                Log.i(TAG, "onResp: 什么原因");
                finish();
                break;
            case BaseResp.ErrCode.ERR_OK:       //正常返回
                //用户同意
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    //用户换取access_token的code，仅在ErrCode为0时有效
                    String code = ((SendAuth.Resp) baseResp).code;
                    Log.i(TAG, "code:------>" + code);

                    //这里拿到了这个code，去做网络请求获取access_token和用户个人信息
                    getAccessToken(code);


                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    showToast(mContext, "微信分享成功");
                    Log.i(TAG, "onResp: 微信分享成功");
                    finish();
                }

                break;

           default:
               Log.i(TAG, "onResp: 其他错误");
               break;
        }

    }

    /**
     * 获取access_token
     * @param code
     */
    private void getAccessToken(String code) {
        Map<String, Object> params = new HashMap();
        params.put("appid", Constant.WXAPPID);
        params.put("secret", Constant.WXAPPSECRET);
        params.put("code", code);
        params.put("grant_type", "authorization_code");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getAccessTokenUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(NetApi.class);
        api.getWXAccessTokenBean(params).enqueue(new Callback<WXAccessTokenBean>() {
            @Override
            public void onResponse(Call<WXAccessTokenBean> call, Response<WXAccessTokenBean> response) {
                if(response!=null&&response.body()!=null){
                    Log.i(TAG, "\n获取到的Accesstoken为："+response.body().getAccess_token()+"\nopenid为："+response.body().getOpenid()+"\nrefreshToken为："+response.body().getRefresh_token());


                    //此处写入Sharepreference中
                    SharedPreferences.Editor editor = getSharedPreferences("WXLoginData", MODE_PRIVATE).edit();
                    editor.putString("access_token",response.body().getAccess_token());//接口调用凭证
                    editor.putString("openid",response.body().getOpenid());//授权用户唯一标识
                    editor.putString("scope",response.body().getScope());//用户授权的作用域，使用逗号（,）分隔
                    editor.apply();
                    finish();





                }else {
                    Log.i(TAG, "onResponse: 为空");
                }
            }

            @Override
            public void onFailure(Call<WXAccessTokenBean> call, Throwable t) {
                Log.i(TAG, "onFailure: 失败");
            }
        });

//        api.getWXAccessTokenBean2(params).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if(response!=null&&response.body()!=null){
//                    try {
//                        String s = new String(response.body().bytes());
//                        Log.i(TAG, "onResponse: "+s);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }else {
//                    Log.i(TAG, "onResponse: response为空");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.i(TAG, "onFailure: 访问错误");
//            }
//        });

//        HttpUtils.getWXAccessTokenBean(URLConstant.URL_WX_BASE, params)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<WXAccessTokenBean>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(TAG, "onCompleted:-------->");
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.i(TAG, "onError:-------->" + throwable.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(WXAccessTokenBean wxAccessTokenBean) {
//                        Log.i(TAG, "onNext: ----->");
//                        String access_token = wxAccessTokenBean.getAccess_token(); //接口调用凭证
//                        String openid = wxAccessTokenBean.getOpenid(); //授权用户唯一标识
//                        //当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
//                        String unionid = wxAccessTokenBean.getUnionid();
//                        Log.i(TAG, "access_token:----->" + access_token);
//                        Log.i(TAG, "openid:----->" + openid);
//                        Log.i(TAG, "unionid:----->" + unionid);
//                        getWXUserInfo(access_token, openid, unionid);
//                    }
//                });
    }


    public void showToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
