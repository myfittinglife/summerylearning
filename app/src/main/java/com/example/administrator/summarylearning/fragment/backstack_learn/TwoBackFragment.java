package com.example.administrator.summarylearning.fragment.backstack_learn;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.summarylearning.R;

public class TwoBackFragment extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_backstack2, container, false);
        return view;
    }
}
