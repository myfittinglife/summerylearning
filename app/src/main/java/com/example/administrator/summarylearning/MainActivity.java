package com.example.administrator.summarylearning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.summarylearning.animation.AnimationActivity;
import com.example.administrator.summarylearning.banneractivity.BannerActivity;
import com.example.administrator.summarylearning.broadcastreceiver.BroadcastActivity;
import com.example.administrator.summarylearning.clearcache.ClearCacheActivity;
import com.example.administrator.summarylearning.commentactivity.CommentActivity;
import com.example.administrator.summarylearning.commonviewpager.CommonViewPagerActivity;
import com.example.administrator.summarylearning.downloadmanager.DownloadManagerActivity;
import com.example.administrator.summarylearning.eventbus.EventBusActivity;
import com.example.administrator.summarylearning.fragment.FragmentContainer;
import com.example.administrator.summarylearning.hanzitransform.HanZiTransformActivity;
import com.example.administrator.summarylearning.interestlablerecyclerview.ActivityInterestLable;
import com.example.administrator.summarylearning.interfacecallback.InterfaceCallbackActivity;
import com.example.administrator.summarylearning.jsbridge.JsBridgeActivity;
import com.example.administrator.summarylearning.littlefeature.LittleFeatureActivity;
import com.example.administrator.summarylearning.materialdesign.MaterialDesignActivity;
import com.example.administrator.summarylearning.mixdevelopment.MixedDevelopActivity;
import com.example.administrator.summarylearning.mvpframework.MvpActivity;
import com.example.administrator.summarylearning.notification.NotificationActivity;
import com.example.administrator.summarylearning.okgo.ActivityOkGo;
import com.example.administrator.summarylearning.pdfactivity.PDFActivity;
import com.example.administrator.summarylearning.permission.PermissionActivity;
import com.example.administrator.summarylearning.pulldownmenu.PullDownActivity;
import com.example.administrator.summarylearning.recyclerview.RecyclerViewActivity;
import com.example.administrator.summarylearning.refreshandloadactivity.RefreshAndLoadActivity;
import com.example.administrator.summarylearning.retrofit.RetrofitActivity;
import com.example.administrator.summarylearning.ringvibrateactivity.RingAndVibrateActivity;
import com.example.administrator.summarylearning.rxjava.RxJavaActivity;
import com.example.administrator.summarylearning.secondarylist.SecondaryListActivity;
import com.example.administrator.summarylearning.selectpicture.SelectPictureActivity;
import com.example.administrator.summarylearning.service.ServiceActivity;
import com.example.administrator.summarylearning.startforresult.StartActivity;
import com.example.administrator.summarylearning.systemsetting.SystemSettingActivity;
import com.example.administrator.summarylearning.text.TextActivity2;
import com.example.administrator.summarylearning.thirdlogin.ThirdLoginActivity;
import com.example.administrator.summarylearning.thirdshare.ShareActivity;
import com.example.administrator.summarylearning.videoplayer.PlayerActivity;
import com.example.administrator.summarylearning.viewpager_tablelayout_fragment.ViewpagerTablelayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author LD
 * @Time
 * @Describe 主页面，各个按钮汇总
 * @Modify
 */
public class MainActivity extends AppCompatActivity {

    private boolean isCanExit=false;      //是否可以退出
    private static final String TAG = "ceshi";


    @BindView(R.id.btn_eventbus_activity)
    Button btn_eventbus_activity;
    @BindView(R.id.btn_start_for_result)
    Button btn_start_for_result;
    @BindView(R.id.btn_okgo_activity)
    Button btn_okgo_activity;
    @BindView(R.id.btn_lable_activity)
    Button btn_lable_activity;
    @BindView(R.id.btn_matisse_activity)
    Button btn_matisse_activity;
    @BindView(R.id.btn_callback)
    Button btn_callback;
    @BindView(R.id.btn_get_phone_info)
    Button btn_get_phone_info;
    @BindView(R.id.btn_expand_list)
    Button btn_expand_list;
    @BindView(R.id.btn_animation)
    Button btn_animation;
    @BindView(R.id.btn_vibrate_ring)     //震动
            Button btn_vibrate_ring;
    @BindView(R.id.btn_fragment)        //Fragment相关
            Button btn_fragment;
    @BindView(R.id.btn_jsbridge)
    Button btn_jsbridge;
    @BindView(R.id.btn_text)
    Button button;
    @BindView(R.id.btn_little_feature)      //小功能
            Button btnLittleFeature;
    @BindView(R.id.btn_mixed_development)   //混合开发
            Button btnMixedDevelopment;
    @BindView(R.id.btn_system_setting)      //系统设置
            Button btnSystemSetting;
    @BindView(R.id.btn_recyclerview)        //RecyclerView使用
            Button btnRecyclerview;
    @BindView(R.id.btn_materialdedign)      //materialdesign
            Button btnMaterialdedign;
    @BindView(R.id.btn_mvp)                 //MVP架构使用
            Button btn_mvp;
    @BindView(R.id.btn_rxjava)              //RxJava使用
            Button btnRxjava;
    @BindView(R.id.btn_retrofit)            //retrofit使用
            Button btnRetrofit;
    @BindView(R.id.btn_notification)        //通知
            Button btnNotification;
    @BindView(R.id.btn_service)             //service下载服务
            Button btnService;
    @BindView(R.id.btn_downloadmanager)     //系统下载管理器
            Button btnDownloadmanager;
    @BindView(R.id.btn_broadcast)           //广播接收器
            Button btnBroadcast;
    @BindView(R.id.btn_comment)             //评论功能
            Button btnComment;
    @BindView(R.id.btn_banner)              //轮播图
            Button btnBanner;
    @BindView(R.id.btn_common_viewpager)     //通用ViewPager
            Button btnCommonViewpager;
    @BindView(R.id.btn_refreshAndload)      //上拉加载下拉刷新
            Button btnRefreshAndload;
    @BindView(R.id.btn_third_login)         //第三方登录
            Button btnThirdLogin;
    @BindView(R.id.btn_permission)          //权限申请
            Button btnPermission;
    @BindView(R.id.btn_third_share)         //第三方分享
            Button btnThirdShare;
    @BindView(R.id.btn_clear_cache)         //清除缓存
            Button btnClearCache;
    @BindView(R.id.btn_pulldown_menu)       //下拉菜单
            Button btnPulldownMenu;
    @BindView(R.id.btn_viewpager)           //滑动切换页面
            Button btnViewpager;
    @BindView(R.id.btn_videoplayer)         //视频播放
            Button btnVideoplayer;
    @BindView(R.id.btn_pdf)                 //PDF文件查看
            Button btnPdf;
    @BindView(R.id.btn_hanzi_transform)     //汉字转拼音
    Button btnHanziTransform;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.i(TAG, "是否为debug状态"+ProjectApp.getInstance().isDebug());
    }


    @OnClick({R.id.btn_eventbus_activity, R.id.btn_start_for_result, R.id.btn_okgo_activity, R.id.btn_lable_activity, R.id.btn_matisse_activity, R.id.btn_callback, R.id.btn_get_phone_info, R.id.btn_expand_list
            , R.id.btn_animation, R.id.btn_vibrate_ring, R.id.btn_fragment, R.id.btn_jsbridge, R.id.btn_text, R.id.btn_little_feature, R.id.btn_mixed_development, R.id.btn_system_setting, R.id.btn_recyclerview
            , R.id.btn_mvp, R.id.btn_materialdedign, R.id.btn_rxjava, R.id.btn_retrofit, R.id.btn_notification, R.id.btn_service, R.id.btn_downloadmanager, R.id.btn_broadcast, R.id.btn_comment, R.id.btn_banner
            , R.id.btn_common_viewpager, R.id.btn_refreshAndload, R.id.btn_third_login, R.id.btn_permission, R.id.btn_third_share, R.id.btn_clear_cache, R.id.btn_pulldown_menu, R.id.btn_viewpager, R.id.btn_videoplayer
            , R.id.btn_pdf,R.id.btn_hanzi_transform})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_eventbus_activity:                                                        //eventbus
                startActivity(new Intent(this, EventBusActivity.class));
                break;
            case R.id.btn_start_for_result:                                                         //startforresult
                startActivity(new Intent(this, StartActivity.class));
                break;
            case R.id.btn_okgo_activity:                                                            //OkGo框架
                startActivity(new Intent(this, ActivityOkGo.class));
                break;
            case R.id.btn_lable_activity:                                                           //兴趣标签
                startActivity(new Intent(this, ActivityInterestLable.class));
                break;
            case R.id.btn_matisse_activity:                                                         //打开相册活动
                startActivity(new Intent(this, SelectPictureActivity.class));
                break;
            case R.id.btn_callback:                                                                 //接口回调
                startActivity(new Intent(this, InterfaceCallbackActivity.class));
                break;
            case R.id.btn_get_phone_info:                                                           //获取手机信息未实现
                Toast.makeText(this, "获取手机信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_expand_list:                                                              //二级列表
                startActivity(new Intent(this, SecondaryListActivity.class));
                break;
            case R.id.btn_animation:
                startActivity(new Intent(this, AnimationActivity.class));                           //动画
                break;
            case R.id.btn_vibrate_ring:
                startActivity(new Intent(this, RingAndVibrateActivity.class));                      //震动响铃
                break;
            case R.id.btn_fragment:                                                                 //Fragment相关
                startActivity(new Intent(this, FragmentContainer.class));
                break;
            case R.id.btn_jsbridge:                                                                 //JsBridge学习
                startActivity(new Intent(this, JsBridgeActivity.class));
                break;
            case R.id.btn_mixed_development:                                                        //混合开发
                startActivity(new Intent(this, MixedDevelopActivity.class));
                break;
            case R.id.btn_text:
                startActivity(new Intent(this, TextActivity2.class));
                break;
            case R.id.btn_little_feature:
                startActivity(new Intent(this, LittleFeatureActivity.class));                       //小功能集合
                break;
            case R.id.btn_system_setting:                                                           //系统设置
                startActivity(new Intent(this, SystemSettingActivity.class));
                break;
            case R.id.btn_recyclerview:
                startActivity(new Intent(this, RecyclerViewActivity.class));                        //RecyclerView使用
                break;
            case R.id.btn_mvp:                                                                      //MVP架构使用
                startActivity(new Intent(this, MvpActivity.class));
                break;
            case R.id.btn_materialdedign:                                                           //MaterialDesign
                startActivity(new Intent(this, MaterialDesignActivity.class));
                break;
            case R.id.btn_rxjava:                                                                   //RxJava使用
                startActivity(new Intent(this, RxJavaActivity.class));
                break;
            case R.id.btn_retrofit:                                                                 //Retrofit使用
                startActivity(new Intent(this, RetrofitActivity.class));
                break;
            case R.id.btn_notification:                                                             //通知使用
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.btn_service:
                startActivity(new Intent(this, ServiceActivity.class));                             //Service服务的使用
                break;
            case R.id.btn_downloadmanager:                                                          //系统下载管理器
                startActivity(new Intent(this, DownloadManagerActivity.class));
                break;
            case R.id.btn_broadcast:                                                                //广播接收器
                startActivity(new Intent(this, BroadcastActivity.class));
                break;
            case R.id.btn_comment:                                                                  //评论功能
                startActivity(new Intent(this, CommentActivity.class));
                break;
            case R.id.btn_banner:                                                                   //轮播图
                startActivity(new Intent(this, BannerActivity.class));
                break;
            case R.id.btn_common_viewpager:                                                         //通用ViewPaper
                startActivity(new Intent(this, CommonViewPagerActivity.class));
                break;
            case R.id.btn_refreshAndload:
                startActivity(new Intent(this, RefreshAndLoadActivity.class));                      //上拉加载下拉刷新
                break;
            case R.id.btn_third_login:
                startActivity(new Intent(this, ThirdLoginActivity.class));                          //第三方登录
                break;
            case R.id.btn_permission:                                                               //权限申请
                startActivity(new Intent(this, PermissionActivity.class));
                break;
            case R.id.btn_third_share:                                                              //第三方分享
                startActivity(new Intent(this, ShareActivity.class));
                break;
            case R.id.btn_clear_cache:                                                              //清除缓存操作
                startActivity(new Intent(this, ClearCacheActivity.class));
                break;
            case R.id.btn_pulldown_menu:                                                            //下拉菜单
                startActivity(new Intent(this, PullDownActivity.class));
                break;
            case R.id.btn_viewpager:                                                                //滑动切换页面
                startActivity(new Intent(this, ViewpagerTablelayoutActivity.class));
                break;
            case R.id.btn_videoplayer:                                                              //视频播放
                startActivity(new Intent(this, PlayerActivity.class));
                break;
            case R.id.btn_pdf:
                startActivity(new Intent(this, PDFActivity.class));                                 //PDF文件查看
                break;
            case R.id.btn_hanzi_transform:
                startActivity(new Intent(this, HanZiTransformActivity.class));                              //汉字转拼音
                break;
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if(isCanExit){
            super.onBackPressed();
            ProjectApp.getInstance().exit();
        }else {
            isCanExit=true;


            Toast.makeText(getApplicationContext(),"再点一次退出程序",Toast.LENGTH_SHORT).show();
            // 获得主线程的looper
            Looper mainLooper = getMainLooper();
            // 获取主线程的handler
            Handler handler = new Handler(mainLooper);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isCanExit=false;
                }
            },5000);
        }


    }
}
