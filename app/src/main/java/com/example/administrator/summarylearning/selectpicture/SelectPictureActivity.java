package com.example.administrator.summarylearning.selectpicture;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.selectpicture.utils.PhotoSelUtil;
import com.zhihu.matisse.Matisse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.administrator.summarylearning.selectpicture.utils.PhotoSelUtil.REQUEST_CODE_CHOOSE;

public class SelectPictureActivity extends AppCompatActivity {


    @BindView(R.id.btn_open_select)
    Button btn_open_select;
    @BindView(R.id.iv_photo)
    ImageView imageView;
    PhotoSelUtil photoSelUtil=new PhotoSelUtil(this);       //主要

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_open_select)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_select:
                photoSelUtil.sel();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK){
            //图片路径 同样视频地址也是这个 根据requestCode
            List<Uri> pathList = Matisse.obtainResult(data);
            Glide.with(this).load(pathList.get(0)).into(imageView);
        }

    }
}
