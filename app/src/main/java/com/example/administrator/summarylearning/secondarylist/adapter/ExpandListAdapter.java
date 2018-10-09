package com.example.administrator.summarylearning.secondarylist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.secondarylist.bean.ExpandDateBean;

import java.util.ArrayList;
import java.util.List;

public class ExpandListAdapter extends BaseExpandableListAdapter {


    private List<ExpandDateBean> dateBeans = new ArrayList<>();
    private Context context;

    public ExpandListAdapter(Context context,List<ExpandDateBean> dateBeans) {
        super();
        this.context = context;
        this.dateBeans = dateBeans;
    }

    @Override
    public int getGroupCount() {            //一级列表的个数
        return dateBeans.size();
    }

    @Override
    public int getChildrenCount(int i) {     //二级列表的个数
        return dateBeans.get(i).getSonList().size();
    }

    @Override
    public Object getGroup(int i) {     //返回一级列表单个item
        return dateBeans.get(i).getFathername();
    }

    @Override
    public Object getChild(int i, int i1) {
        return dateBeans.get(i).getSonList().get(i1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //每个item的id是否是固定？一般为true
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //【重要】填充一级列表
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_expand_father, null);
        }
        TextView tv_group = (TextView) convertView.findViewById(R.id.tv_father);
        tv_group.setText(dateBeans.get(groupPosition).getFathername());
        return convertView;
    }

    //【重要】填充一级列表
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_expand_son, null);
        }

        TextView tv_child = (TextView) convertView.findViewById(R.id.tv_son );
        tv_child.setText(dateBeans.get(groupPosition).getSonList().get(childPosition));
        return convertView;
    }
    //二级列表中的item是否能够被选中？可以改为true
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
