package com.example.administrator.summarylearning.fragment.backstack_learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.summarylearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OneBackFragment extends Fragment {
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.btn_common_goto)
    Button btnCommonGoto;
    @BindView(R.id.btn_stack_goto)
    Button btnStackGoto;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_backstack1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_common_goto, R.id.btn_stack_goto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_common_goto:      //普通方式替换

                TwoBackFragment fragment = new TwoBackFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.ll_fragment_container1, fragment);
                transaction.commitAllowingStateLoss();

                break;
            case R.id.btn_stack_goto:           //回退栈方式替换
                break;
        }
    }

}
