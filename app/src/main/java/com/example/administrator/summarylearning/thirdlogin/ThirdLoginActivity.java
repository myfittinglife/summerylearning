package com.example.administrator.summarylearning.thirdlogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.summarylearning.Constant;
import com.example.administrator.summarylearning.R;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author
 * @Time
 * @Describe 第三方登录
 * @Modify
 */
public class ThirdLoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_qq_login)
    Button btnQqLogin;
    @BindView(R.id.btn_weixin_login)
    Button btnWeixinLogin;


    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI wxapi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_qq_login,R.id.btn_weixin_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_qq_login:             //QQ登录
                Toast.makeText(getApplicationContext(),"QQ登录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_weixin_login:         //微信登录
                if(wxapi==null){
                    // 通过WXAPIFactory工厂，获取IWXAPI的实例
                    wxapi = WXAPIFactory.createWXAPI(this, Constant.WXAPPID, false);

                    // 将应用的appId注册到微信
                    wxapi.registerApp(Constant.WXAPPID);
                }
                if (!wxapi.isWXAppInstalled()) {
                    Toast.makeText(this, "没有安装微信,请先安装微信!", Toast.LENGTH_SHORT).show();
                    return;
                }
                regToWx();


                break;
            default:
                break;
        }
    }

    /**
     * 向微信终端注册appID,请求完会回调WXEntryActivity中的onResp()方法
     */
    private void regToWx() {
        //微信登录，请求CODE
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "sigintzh";
        wxapi.sendReq(req);
    }



}
