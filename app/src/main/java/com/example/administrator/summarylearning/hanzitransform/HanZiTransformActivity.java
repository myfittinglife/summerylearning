package com.example.administrator.summarylearning.hanzitransform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.hanzitransform.util.PinyinHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author      LD
 * @Time        2018/12/15
 * @Describe    汉字转拼音
 * @Modify
 */
public class HanZiTransformActivity extends AppCompatActivity {

    @BindView(R.id.et_hanzi)            //汉字框
    EditText etHanzi;
    @BindView(R.id.btn_to_fon)
    Button btnToFon;
    @BindView(R.id.btn_to_simple)
    Button btnToSimple;
    @BindView(R.id.btn_fon_to_pinyin)
    Button btnFonToPinyin;
    @BindView(R.id.btn_simple_to_pinyin)
    Button btnSimpleToPinyin;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_han_zi_transform);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_to_fon, R.id.btn_to_simple, R.id.btn_fon_to_pinyin, R.id.btn_simple_to_pinyin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_to_fon:               //简转繁


                break;
            case R.id.btn_to_simple:            //繁转简


                break;
            case R.id.btn_fon_to_pinyin:        //繁转拼音
                if(!TextUtils.isEmpty(etHanzi.getText())){
                    String pinStr = PinyinHelper.convertToPinyinString(etHanzi.getText().toString(),"__");
                    tvContent.setText(pinStr);
                }
                break;
            case R.id.btn_simple_to_pinyin:     //简转拼音
                if(!TextUtils.isEmpty(etHanzi.getText())){
                    String pinStr = PinyinHelper.convertToPinyinString(etHanzi.getText().toString(),"__");
                    tvContent.setText(pinStr);
                }
                break;
        }
    }
}
