package com.example.administrator.summarylearning.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @Author LD
 * @Time 2018/11/20
 * @Describe 选择进入哪一种recyclerview
 * @Modify
 */
public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.btn_slide)
    Button btnSlide;
    @BindView(R.id.btn_multi_select)
    Button btnMultiSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_slide, R.id.btn_multi_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_slide:                                                                    //侧滑删除recyclerview
                startActivity(new Intent(this, SlideRecyclerViewActivity.class));
                break;
            case R.id.btn_multi_select:                                                             //多选recyclerview
                startActivity(new Intent(this, MultiChoiceActivity.class));
                break;
        }
    }


}
