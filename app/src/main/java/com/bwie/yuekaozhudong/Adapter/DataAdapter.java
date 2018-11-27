package com.bwie.yuekaozhudong.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bwie.yuekaozhudong.R;
import com.bwie.yuekaozhudong.Sql.SqlBean;

import java.util.ArrayList;
import java.util.List;


public class DataAdapter extends BaseAdapter {

    private List<SqlBean> list = new ArrayList<>();
    private Context context;

    public DataAdapter(Context context) {
        this.context = context;
    }
    public void setList(List<SqlBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyView mv;
            if(view==null){
                mv = new MyView();
                view = View.inflate(context, R.layout.data_item,null);
                mv.data_content = view.findViewById(R.id.data_content);
                mv.data_itemdata = view.findViewById(R.id.data_itemdata);
                view.setTag(mv);
            }else{
                mv = (MyView) view.getTag();
            }
        mv.data_content.setText(list.get(i).getContent());
        mv.data_itemdata.setText(list.get(i).getItemdata());
        return view;
    }

    class MyView{
        public TextView data_content;
        public TextView data_itemdata;
    }

}
