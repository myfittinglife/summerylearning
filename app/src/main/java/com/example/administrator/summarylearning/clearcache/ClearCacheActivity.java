package com.example.administrator.summarylearning.clearcache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.utils.DataCleanManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author      LD
 * @Time        2018/12/11
 * @Describe    清除缓存操作（待完善，后续增添dialog框来进行提醒）
 * @Modify
 */
public class ClearCacheActivity extends AppCompatActivity {

    @BindView(R.id.text_cache)
    TextView textCache;
    @BindView(R.id.btn_clear_cache)
    Button btnClearCache;
    @BindView(R.id.btn_query)
    Button btnQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_cache);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_clear_cache, R.id.btn_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_clear_cache:
                DataCleanManager.cleanApplicationData(this, getCacheDir().getAbsolutePath());
                break;
            case R.id.btn_query:
                textCache.setText(DataCleanManager.getTotalCacheSize(this));
                break;
        }
    }
}
