package com.example.administrator.summarylearning.fragment.fragmentlifecycle;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.summarylearning.R;

import org.greenrobot.eventbus.EventBus;

/**
 * 用于写生命周期日志的碎片一
 */
public class OneFragment extends Fragment {


    private static final String TAG = "OneBackFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendLog("onAttach");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendLog("onCreate");
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        sendLog("onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        sendLog("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        sendLog("onStart");
        super.onStart();
    }
    @Override
    public void onResume() {
        sendLog("onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        sendLog("onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        sendLog("onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        sendLog("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        sendLog("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        sendLog("onDetach");
        super.onDetach();
    }
    public void sendLog(String str){
        EventBus.getDefault().post(new LogEvent(TAG+"："+str+"\n"));
    }
}
