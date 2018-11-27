package com.bwie.yuekaozhudong.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bwie.yuekaozhudong.DateBean;
import com.bwie.yuekaozhudong.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class XlistViewAdapter extends BaseAdapter {

    private List<DateBean.DataBean> list = new ArrayList<>();
    private Context context;

    public XlistViewAdapter(Context context) {
        this.context = context;
    }
    public void setList(List<DateBean.DataBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    private static final int ONETYPE = 0;
    private static final int TWOTYPE = 1;
    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getThumbnail_pic_s03() != null){
            return TWOTYPE;
        }else {
            return ONETYPE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
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
        int viewType = getItemViewType(i);
        if(viewType==ONETYPE){
            MyOneView mov;
            if(view==null){
                mov = new MyOneView();
                view = View.inflate(context, R.layout.item_one,null);
                mov.item_one_title = view.findViewById(R.id.item_one_title);
                mov.item_one_content = view.findViewById(R.id.item_one_content);
                mov.item_one_img = view.findViewById(R.id.item_one_img);
                view.setTag(mov);
            }else{
                mov = (MyOneView) view.getTag();
            }
            mov.item_one_title.setText(list.get(i).getTitle());
            mov.item_one_content.setText(list.get(i).getDate());
            Picasso.get().load(list.get(i).getThumbnail_pic_s()).into(mov.item_one_img);
        }else{
            MyTwoView mtv;
            if(view==null){
                mtv = new MyTwoView();
                view = View.inflate(context, R.layout.item_two,null);
                mtv.item_two_title = view.findViewById(R.id.item_two_title);
                mtv.item_two_img1 = view.findViewById(R.id.item_two_img1);
                mtv.item_two_img2 = view.findViewById(R.id.item_two_img2);
                mtv.item_two_img3 = view.findViewById(R.id.item_two_img3);
                view.setTag(mtv);
            }else{
                mtv = (MyTwoView) view.getTag();
            }
            mtv.item_two_title.setText(list.get(i).getTitle());
            Picasso.get().load(list.get(i).getThumbnail_pic_s()).into(mtv.item_two_img1);
            Picasso.get().load(list.get(i).getThumbnail_pic_s02()).into(mtv.item_two_img2);
            Picasso.get().load(list.get(i).getThumbnail_pic_s03()).into(mtv.item_two_img3);
        }
        return view;
    }

    class MyOneView{
        public TextView item_one_title;
        public TextView item_one_content;
        public ImageView item_one_img;
    }

    class MyTwoView{
        public TextView item_two_title;
        public ImageView item_two_img1;
        public ImageView item_two_img2;
        public ImageView item_two_img3;
    }
}
