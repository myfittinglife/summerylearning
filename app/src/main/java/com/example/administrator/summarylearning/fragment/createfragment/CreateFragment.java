package com.example.administrator.summarylearning.fragment.createfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.summarylearning.ProjectApp;
import com.example.administrator.summarylearning.R;
import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 通过Argument方式创建Fragment并传参
 */
public class CreateFragment extends Fragment {

    @BindView(R.id.tv_argument)
    TextView tvArgument;
    Unbinder unbinder;

    //创建fragment的方式
    public static CreateFragment newInstance(String title) {
        CreateFragment createFragment = new CreateFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        createFragment.setArguments(bundle);
        return createFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (getArguments() != null) {       //获得的参数不为空
            tvArgument.setText(getArguments().getString("title"));
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //检测内存泄漏
        RefWatcher refWatcher = ProjectApp.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
