package com.example.administrator.summarylearning.secondarylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.administrator.summarylearning.R;
import com.example.administrator.summarylearning.secondarylist.adapter.ExpandListAdapter;
import com.example.administrator.summarylearning.secondarylist.bean.ExpandDateBean;

import java.util.ArrayList;
import java.util.List;

public class SecondaryListActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandListAdapter expandListAdapter;
    private List<ExpandDateBean> dateBeans = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_list);

        expandableListView = findViewById(R.id.expand_list);
        expandableListView.setGroupIndicator(null);     //取消掉向下的箭头
        initDate();
        expandListAdapter = new ExpandListAdapter(this,dateBeans);
        expandableListView.setAdapter(expandListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getApplicationContext(),dateBeans.get(i).getFathername()+":"+dateBeans.get(i).getSonList().get(i1),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
    private void initDate(){
        ExpandDateBean date1 = new ExpandDateBean();
        date1.setFathername("列表一");
        List<String> son1 = new ArrayList<>();
        son1.add("1");
        son1.add("2");
        son1.add("3");
        date1.setSonList(son1);

        ExpandDateBean date2 = new ExpandDateBean();
        date2.setFathername("列表二");
        List<String> son2 = new ArrayList<>();
        son2.add("1");
        son2.add("2");
        son2.add("3");
        date2.setSonList(son2);

        ExpandDateBean date3 = new ExpandDateBean();
        date3.setFathername("列表三");
        List<String> son3 = new ArrayList<>();
        son3.add("1");
        son3.add("2");
        son3.add("3");
        date3.setSonList(son3);
        dateBeans.add(date1);
        dateBeans.add(date2);
        dateBeans.add(date3);
    }
}
