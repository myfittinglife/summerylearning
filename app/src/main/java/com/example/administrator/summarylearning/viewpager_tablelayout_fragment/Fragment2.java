package com.example.administrator.summarylearning.viewpager_tablelayout_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.summarylearning.R;

/**
 * @Author
 * @Time 2018/12/13 10:51
 * @Describe
 * @Modify
 */
public class Fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_fragment2, container, false);
        return view;
    }
}
