package com.example.administrator.summarylearning.recyclerview.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.recyclerview.bean.BasicBean;

import java.util.List;

/**
 * 作者    LD
 * 时间    2018/11/16 10:39
 * 描述    普通的Adapter
 */
public class SlideBasicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<BasicBean> mList;
    private Context context;
    private onClickDelete listener;

    public SlideBasicAdapter(Context context, List<BasicBean> list, onClickDelete listener) {
        this.context = context;
        mList = list;
        this.listener = listener;
    }

    //创建ViewHolder实例
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if (viewType == R.layout.item_recycle_samll) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_samll, parent, false);
            final ViewHolderSmall viewHolder = new ViewHolderSmall(view);
            viewHolder.slideItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "小布局的删除行为", Toast.LENGTH_SHORT).show();
                    int position = viewHolder.getAdapterPosition();
                    listener.onDelete(position);
                }
            });


            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_big, parent, false);
            final ViewHolderBig viewHolder = new ViewHolderBig(view);
            viewHolder.slideItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "大布局的删除行为", Toast.LENGTH_SHORT).show();
                    int position = viewHolder.getAdapterPosition();
                    listener.onDelete(position);
                }
            });
            return viewHolder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 4 == 0) {
            return R.layout.item_recycle_big;
        } else {
            return R.layout.item_recycle_samll;
        }
    }

    //对子项数据进行赋值
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        BasicBean src = mList.get(position);
        if (position % 4 == 0) {
            ViewHolderBig holderBig = (ViewHolderBig) viewHolder;
            Glide.with(context).load(src.getUrl()).into(holderBig.imageViewBig);
            holderBig.titleBig.setText(src.getTitle());
            holderBig.nameBig.setText(src.getName());
            holderBig.favoritesBig.setText(src.getFavorites());
            holderBig.commentsBig.setText(src.getComments());
//            holderBig.slideItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, "大布局的删除行为", Toast.LENGTH_SHORT).show();
//                    listener.onDelete(position);
//                }
//            });
        } else {
            ViewHolderSmall holderSmall = (ViewHolderSmall) viewHolder;
            Glide.with(context).load(src.getUrl()).into(holderSmall.imageView);
            holderSmall.title.setText(src.getTitle());
            holderSmall.name.setText(src.getName());
            holderSmall.favorites.setText(src.getFavorites());
            holderSmall.comments.setText(src.getComments());
//            holderSmall.slideItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, "小布局的删除行为", Toast.LENGTH_SHORT).show();
//                    listener.onDelete(position);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {     //返回item条码的数目
        return mList.size();
    }


    public static class ViewHolderSmall extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView name;
        TextView favorites;
        TextView comments;
        public TextView slideItem;


        public ViewHolderSmall(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item);            //图片
            title = itemView.findViewById(R.id.item_title);             //标题
            name = itemView.findViewById(R.id.item_name);               //姓名
            favorites = itemView.findViewById(R.id.item_favorites);     //点赞
            comments = itemView.findViewById(R.id.item_comments);       //评论
            slideItem = itemView.findViewById(R.id.slide_item);         //删除

        }

    }

    public static class ViewHolderBig extends RecyclerView.ViewHolder {

        ImageView imageViewBig;
        TextView titleBig;
        TextView nameBig;
        TextView favoritesBig;
        TextView commentsBig;
        public TextView slideItem;

        public ViewHolderBig(@NonNull View itemView) {
            super(itemView);
            imageViewBig = itemView.findViewById(R.id.item_big_image);          //图片
            titleBig = itemView.findViewById(R.id.item_big_title);             //标题
            nameBig = itemView.findViewById(R.id.item_big_name);               //姓名
            favoritesBig = itemView.findViewById(R.id.item_big_favorites);     //点赞
            commentsBig = itemView.findViewById(R.id.item_big_comments);       //评论
            slideItem = itemView.findViewById(R.id.slide_item);         //删除

        }

    }

    public interface onClickDelete {
        void onDelete(int positon);
    }


}
