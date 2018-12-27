package com.example.administrator.summarylearning.commentactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.commentactivity.adapter.CommentAdapter;
import com.example.administrator.summarylearning.commentactivity.bean.CommentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author      LD
 * @Time        2018/12/26
 * @Describe    评论功能实现
 * @Modify
 */
public class CommentActivity extends AppCompatActivity {

    @BindView(R.id.tv_write_comment)
    TextView tvWriteComment;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    private CommentAdapter commentAdapter;

    private List<CommentBean> commentBeanList;

    private String headUrl1 = "https://wx3.sinaimg.cn/mw690/49fa6dc0gy1fyl3g93zumj22gw3pckjm.jpg";
    private String headUrl2 = "https://wx4.sinaimg.cn/mw690/49fa6dc0gy1fxrdtf8chyj20zk0zkn7i.jpg";
    private String headUrl3 = "https://wx4.sinaimg.cn/mw690/49fa6dc0ly1fxelnz68hwj215o15maur.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initData();
        commentAdapter = new CommentAdapter(commentBeanList,this);
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.setAdapter(commentAdapter);


    }

    @OnClick({R.id.tv_write_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_write_comment:
                break;
            default:
                break;
        }
    }
    //填充数据
    public void initData(){
        commentBeanList = new ArrayList<>();
        //*------------------------------------------对话一------------------------------------------
        //一级评论
        CommentBean firstFloorComment1 = new CommentBean();
        firstFloorComment1.setContent("强大");
        CommentBean.UserBean FirstFloorUserBean1 = new CommentBean.UserBean("孙梦露",headUrl1);
        firstFloorComment1.setFirstFloorUserBean(FirstFloorUserBean1);

        //二级评论
        List<CommentBean.childBean> secondFloorComment = new ArrayList<>();

        //1
        CommentBean.childBean secondFloorChildBean1 = new CommentBean.childBean();
        CommentBean.UserBean secondFromUser1 = new CommentBean.UserBean("孙梦凡","");
        secondFloorChildBean1.setFromUserBean(secondFromUser1);
        secondFloorChildBean1.setToUserBean(null);
        secondFloorChildBean1.setChildContent("是的");
        secondFloorComment.add(secondFloorChildBean1);

        //2
        CommentBean.childBean secondFloorChildBean2 = new CommentBean.childBean();
        CommentBean.UserBean secondFromUser2 = new CommentBean.UserBean("孙礼","");
        CommentBean.UserBean secondToUser2 = new CommentBean.UserBean("孙梦凡","");
        secondFloorChildBean2.setFromUserBean(secondFromUser2);
        secondFloorChildBean2.setToUserBean(secondToUser2);
        secondFloorChildBean2.setChildContent("嗯");
        secondFloorComment.add(secondFloorChildBean2);
        //一整段评论填入
        firstFloorComment1.setSecondFloorComment(secondFloorComment);
        commentBeanList.add(firstFloorComment1);



        //*---------------------------------------------对话二---------------------------------------
        //一级评论
        CommentBean firstFloorComment2 = new CommentBean();
        firstFloorComment2.setContent("热烈祝贺");
        CommentBean.UserBean FirstFloorUserBean2 = new CommentBean.UserBean("孙礼",headUrl2);
        firstFloorComment2.setFirstFloorUserBean(FirstFloorUserBean2);

        commentBeanList.add(firstFloorComment2);

        //*--------------------------------------------对话三
        //一级评论
        CommentBean firstFloorComment3 = new CommentBean();
        firstFloorComment3.setContent("我在你身后，就差你回头");
        CommentBean.UserBean FirstFloorUserBean3 = new CommentBean.UserBean("卫奕瑄",headUrl3);
        firstFloorComment3.setFirstFloorUserBean(FirstFloorUserBean3);

        //二级评论
        List<CommentBean.childBean> secondFloorComment3 = new ArrayList<>();

        //1
        CommentBean.childBean secondFloorChildBean3 = new CommentBean.childBean();
        CommentBean.UserBean secondFromUser3 = new CommentBean.UserBean("李栋","");
        secondFloorChildBean3.setFromUserBean(secondFromUser3);
        secondFloorChildBean3.setToUserBean(null);
        secondFloorChildBean3.setChildContent("没反应");
        secondFloorComment3.add(secondFloorChildBean3);

        //2
        CommentBean.childBean secondFloorChildBean4 = new CommentBean.childBean();
        CommentBean.UserBean secondFromUser4 = new CommentBean.UserBean("卫奕瑄","");
        CommentBean.UserBean secondToUser4 = new CommentBean.UserBean("李栋","");
        secondFloorChildBean4.setFromUserBean(secondFromUser4);
        secondFloorChildBean4.setToUserBean(secondToUser4);
        secondFloorChildBean4.setChildContent("你点错了吧老哥");
        secondFloorComment3.add(secondFloorChildBean4);
        //一整段评论填入
        firstFloorComment3.setSecondFloorComment(secondFloorComment3);
        commentBeanList.add(firstFloorComment3);
    }


}
