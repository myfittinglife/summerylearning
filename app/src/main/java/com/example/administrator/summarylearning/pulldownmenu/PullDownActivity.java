package com.example.administrator.summarylearning.pulldownmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author          LD
 * @Time            2018/12/11
 * @Describe        下拉菜单
 * @Modify
 */
public class PullDownActivity extends AppCompatActivity implements MyPopwindow.selectedListener, ListPopwindow.theSelectedListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.iv_region)
    ImageView ivRegion;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    private List<String> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_down);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        list.add("全部");
        list.add("农、林、牧、渔业");
        list.add("采矿业");
        list.add("制造业");
        list.add("电力、热力、燃气及水生产和供应业");
        list.add("建造业");
        list.add("批发和零售业");
        list.add("交通运输、仓储和邮政业");
        list.add("住宿和餐饮业");
        list.add("信息传输、软件和信息技术服务业");
        list.add("金融业");
        list.add("房地产业");
        list.add("租赁和商务服务业");
        list.add("科学研究和技术服务业");
        list.add("水利、环境和公共设施管理业");
        list.add("居民服务、修理和其他服务业");
        list.add("教育");
        list.add("卫生和社会工作");
        list.add("文化、体育和娱乐业");
        list.add("公共管理、社会保障和社会组织");
        list.add("国际组织");
        list.add("农、林、牧、渔业");
        list.add("采矿业");
        list.add("制造业");
        list.add("电力、热力、燃气及水生产和供应业");
        list.add("建造业");
        list.add("批发和零售业");
        list.add("交通运输、仓储和邮政业");
        list.add("住宿和餐饮业");
        list.add("信息传输、软件和信息技术服务业");
        list.add("金融业");
        list.add("房地产业");
        list.add("租赁和商务服务业");
        list.add("科学研究和技术服务业");
        list.add("水利、环境和公共设施管理业");
        list.add("居民服务、修理和其他服务业");
        list.add("教育");
        list.add("卫生和社会工作");
        list.add("文化、体育和娱乐业");
        list.add("公共管理、社会保障和社会组织");
        list.add("国际组织");


    }

    @OnClick({R.id.tv_title, R.id.tv_region})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                ivTitle.animate().setDuration(500).rotation(180).start();
                ListPopwindow listPopwindow = new ListPopwindow(this,this,list);
                listPopwindow.showAsDropDown((View) view.getParent());
                break;
            case R.id.tv_region:
                //旋转0度是复位ImageView
                ivRegion.animate().setDuration(500).rotation(180).start();
                MyPopwindow myPopwindow = new MyPopwindow(this, this);
                if (myPopwindow.isShowing()) {
                    Log.i("1212ceshi", "popwindow已弹出");
                } else {
                    Log.i("1212ceshi", "popwindow未弹出");
                }

                myPopwindow.showAsDropDown((View) view.getParent());
//                myPopwindow.showAtLocation(tvRegion,Gravity.TOP,0,0);//相对于哪个控件、、X,Y方向偏移量
                break;
            default:
                break;
        }
    }

    //选择了某个
    @Override
    public void setText(String s) {
        tvRegion.setText(s);
        //旋转0度是复位ImageView
        ivRegion.animate().setDuration(500).rotation(0).start();

    }

    //未选中
    @Override
    public void selectnull() {
        //旋转0度是复位ImageView
        ivRegion.animate().setDuration(500).rotation(0).start();

    }

    @Override
    public void setText2(String s) {
        tvTitle.setText(s);
        //旋转0度是复位ImageView
        ivTitle.animate().setDuration(500).rotation(0).start();

    }

    @Override
    public void selectnull2() {
        //旋转0度是复位ImageView
        ivTitle.animate().setDuration(500).rotation(0).start();
    }
}
