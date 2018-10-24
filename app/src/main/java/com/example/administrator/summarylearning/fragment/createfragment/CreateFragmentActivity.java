package com.example.administrator.summarylearning.fragment.createfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateFragmentActivity extends AppCompatActivity {


    @BindView(R.id.ll_container)
    LinearLayout llContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fragment);
        ButterKnife.bind(this);

        CreateFragment createFragment = CreateFragment.newInstance("我是FunctionListActivity给CreateFragment传的参数");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.ll_container, createFragment);
        transaction.commitAllowingStateLoss();
    }
}
