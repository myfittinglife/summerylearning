package com.example.administrator.summarylearning.viewpager_tablelayout_fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author      LD
 * @Time        2018/12/13
 * @Describe    viewpager+tablelayout+fragment实现滑动切换页面
 * @Modify
 */
public class ViewpagerTablelayoutActivity extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpaper)
    ViewPager viewpaper;

    private ViewPaperAdapter viewPaperAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tableTitleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_tablelayout);
        ButterKnife.bind(this);
        initData();
    }

    public void initData(){
        tablayout.addTab(tablayout.newTab());
        tablayout.addTab(tablayout.newTab());      //记住此处是所选的tablayout的newTab()而不是new TabLayout.Tab()
        tablayout.setupWithViewPager(viewpaper);    //和viewpager联动
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        tableTitleList.add("标题一");
        tableTitleList.add("标题二");

        viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(), fragmentList,tableTitleList);
        viewpaper.setAdapter(viewPaperAdapter);
        viewpaper.setCurrentItem(0);
//        tablayout.setOnTabSelectedListener(this);过时，建议用下面那个，但监听器接口一致
        tablayout.addOnTabSelectedListener(this);



    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        updateTabTextView(tab,true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        updateTabTextView(tab,false);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {       //选中时文字加粗用的
        if (isSelect) {
            try {
                java.lang.reflect.Field fieldView = tab.getClass().getDeclaredField("mView");
                fieldView.setAccessible(true);
                View view = (View) fieldView.get(tab);
                java.lang.reflect.Field fieldTxt = view.getClass().getDeclaredField("mTextView");
                fieldTxt.setAccessible(true);
                TextView tabSelect = (TextView) fieldTxt.get(view);
                tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tabSelect.setText(tab.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                java.lang.reflect.Field fieldView = tab.getClass().getDeclaredField("mView");
                fieldView.setAccessible(true);
                View view = (View) fieldView.get(tab);
                java.lang.reflect.Field fieldTxt = view.getClass().getDeclaredField("mTextView");
                fieldTxt.setAccessible(true);
                TextView tabSelect = (TextView) fieldTxt.get(view);
                tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tabSelect.setText(tab.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
