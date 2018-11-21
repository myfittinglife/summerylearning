package com.example.administrator.summarylearning.materialdesign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author LD
 * @Time 2018/11/20
 * @Describe MaterialDesign使用
 * @Modify
 */
public class MaterialDesignActivity extends AppCompatActivity {

    /**
     * 头像
     */
    @BindView(R.id.iv_headicon)
    CircleImageView ivHeadicon;
    /**
     * 加号
     */
    @BindView(R.id.toolbar_normal)
    Toolbar toolbarNormal;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;

    /**
     * 滑动菜单中的头像
     */
    @BindView(R.id.iv_swip_headicon)
    CircleImageView ivSwipHeadicon;
    /**
     * 名字
     */
    @BindView(R.id.tv_name)
    TextView tvName;
    /**
     * 背景图
     */
    @BindView(R.id.rel_background)
    RelativeLayout relBackground;

    private static final String TAG = "1121ceshi";
    /**
     * 背景图片
     */
    @BindView(R.id.iv_background)
    ImageView ivBackground;
    /**
     * 悬浮按钮
     */
    @BindView(R.id.btn_fab)
    FloatingActionButton btnFab;

    private String headUrl = "http://a.hiphotos.baidu.com/baike/pic/item/64380cd7912397dd11d619e15e82b2b7d1a287ac.jpg";
    private String backgroundUrl = "http://pic.58pic.com/58pic/14/11/07/55U58PICnSg_1024.jpg";
    private String backgroundUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542793630474&di=caa76fd3dbbe1b4582536121ecd568f5&imgtype=0&src=http%3A%2F%2Fwx4.sinaimg.cn%2Flarge%2F005DIcudly1ftodad3alrj30qo0qogq3.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        ButterKnife.bind(this);
        Glide.with(this).load(headUrl).into(ivHeadicon);
        Glide.with(this).load(headUrl).into(ivSwipHeadicon);
        Glide.with(this).load(backgroundUrl2).into(ivBackground);

//          尚未解决，会使toolbar太向上，遮盖了一部分
//        //用来将背景图和状态栏融合在一起
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();//拿到当前活动的DecorView
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//改变系统UI的显示    活动的布局会显示在状态栏上
//            getWindow().setStatusBarColor(Color.TRANSPARENT); //将状态栏设置为透明色
//        }
//----------------------------------------

//        RequestOptions requestOptions = new RequestOptions();
//        final RequestBuilder<Bitmap> requestBuilder = Glide.with(this).asBitmap();
//        requestBuilder.apply(requestOptions);
//        Glide.with(this).load(headUrl).apply(requestOptions).into(new SimpleTarget<Drawable>() {
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                relBackground.setBackground(resource);
//            }
//        });


        //将Toolbar的实例传入

//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar!=null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
//        }
//        getLayoutInflater().inflate(R.layout.ceshitoolar, toolbar);
//        setSupportActionBar(toolbar);

    }

//    /**
//     * 加载菜单文件
//     *
//     * @param menu
//     * @return
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar, menu);
//        return true;
//    }
//
//    /**
//     * Toolbar子项的点击事件
//     *
//     * @param item
//     * @return
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.backup:
//                ToastUtil("backup");
//                break;
//            case R.id.delete:
//                ToastUtil("delete");
//                break;
//            case R.id.settings:
//                ToastUtil("settings");
//                break;
//            case android.R.id.home:
//                drawerlayout.openDrawer(GravityCompat.START);
//                break;
//            default:
//                break;
//        }
//        return true;
//
//    }


    @OnClick({R.id.iv_headicon, R.id.ll_add,R.id.btn_fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_headicon:
                drawerlayout.openDrawer(GravityCompat.START);
                break;
            case R.id.ll_add:
                ToastUtil("添加");
                break;
            case R.id.btn_fab:
//                ToastUtil("悬浮按钮");
                SnackbarToast(view);
                break;
        }
    }


    public void ToastUtil(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
    public void SnackbarToast(View view){
        Snackbar.make(view,"Data Deleted",Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {     //传入view是为了让coordinatorlayout监听到其子控件FloatingActionButton的行为
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"撤回",Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

}
