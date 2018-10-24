package com.example.administrator.summarylearning.fragment.backstack_learn;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.summarylearning.R;

/**
 * 活动整体放置OneBackFragment
 * 回退栈学习
 */
public class BackStackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_stack);

        OneBackFragment fragment = new OneBackFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.ll_fragment_container1, fragment);
        transaction.commitAllowingStateLoss();
    }
}
