package com.example.administrator.summarylearning.recyclerview.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.recyclerview.model.BasicModel;

import java.util.List;

/**
 * 作者    LD
 * 时间    2018/11/16 10:39
 * 描述    普通的Adapter
 */
public class BasicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<BasicModel> mList;
    private Context context;

    public BasicAdapter(Context context,List<BasicModel> list) {
        this.context = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        if(viewType==R.layout.item_recycle){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle,parent,false);
            ViewHolderSmall viewHolder = new ViewHolderSmall(view);
            return viewHolder;
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_big,parent,false);
            ViewHolderBig viewHolder = new ViewHolderBig(view);
            return viewHolder;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position%4==0){
            return R.layout.item_recycle_big;
        }else {
            return R.layout.item_recycle;
        }
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder, int position) {
        BasicModel src = mList.get(position);
        if(position%4==0){
            ViewHolderBig holderBig = (ViewHolderBig) viewHolder;
            Glide.with(context).load(src.getUrl()).into(holderBig.imageViewBig);
            holderBig.titleBig.setText(src.getTitle());
            holderBig.nameBig.setText(src.getName());
            holderBig.favoritesBig.setText(src.getFavorites());
            holderBig.commentsBig.setText(src.getComments());
        }else {
            ViewHolderSmall holderSmall = (ViewHolderSmall) viewHolder;
            Glide.with(context).load(src.getUrl()).into(holderSmall.imageView);
            holderSmall.title.setText(src.getTitle());
            holderSmall.name.setText(src.getName());
            holderSmall.favorites.setText(src.getFavorites());
            holderSmall.comments.setText(src.getComments());
        }


    }
    @Override
    public int getItemCount() {     //返回item条码的数目
        return mList.size();
    }

    static class ViewHolderSmall extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView  title,name,favorites,comments;


        public ViewHolderSmall(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item);            //图片
            title = itemView.findViewById(R.id.item_title);             //标题
            name = itemView.findViewById(R.id.item_name);               //姓名
            favorites = itemView.findViewById(R.id.item_favorites);     //点赞
            comments = itemView.findViewById(R.id.item_comments);       //评论

        }
    }
    static class ViewHolderBig extends RecyclerView.ViewHolder{

        ImageView imageViewBig;
        TextView  titleBig,nameBig,favoritesBig,commentsBig;

        public ViewHolderBig(@NonNull View itemView) {
            super(itemView);
            imageViewBig = itemView.findViewById(R.id.item_big_image);          //图片
            titleBig = itemView.findViewById(R.id.item_big_title);             //标题
            nameBig = itemView.findViewById(R.id.item_big_name);               //姓名
            favoritesBig = itemView.findViewById(R.id.item_big_favorites);     //点赞
            commentsBig = itemView.findViewById(R.id.item_big_comments);       //评论
        }
    }



}
