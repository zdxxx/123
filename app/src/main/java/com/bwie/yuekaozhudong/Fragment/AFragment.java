package com.bwie.yuekaozhudong.Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bwie.yuekaozhudong.Adapter.DataAdapter;
import com.bwie.yuekaozhudong.Adapter.XlistViewAdapter;
import com.bwie.yuekaozhudong.DateBean;
import com.bwie.yuekaozhudong.R;
import com.bwie.yuekaozhudong.Sql.Dao;
import com.bwie.yuekaozhudong.Sql.SqlBean;
import com.example.xlistviewlib.XListView;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class AFragment extends Fragment implements XListView.IXListViewListener{

    private XListView xListView;
    private static final String path = "http://www.xieast.com/api/news/news.php?page=";
    private int page = 1;
    private List<DateBean.DataBean> listAll = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String obj = (String) msg.obj;
            DateBean bean = new Gson().fromJson(obj, DateBean.class);
            List<DateBean.DataBean> list = bean.getData();
            listAll.addAll(list);
            viewAdapter.setList(listAll);
            xListView.stopRefresh();
            xListView.stopLoadMore();
            List<SqlBean> query = dao.Query();
            if(query.isEmpty()){
                for(int i = 0 ; i<listAll.size() ; i++){
                    SqlBean sqlBean = new SqlBean(i, listAll.get(i).getTitle(), listAll.get(i).getDate());
                    dao.Add(sqlBean);
                }
                Toast.makeText(getActivity(),"数据缓存成功",Toast.LENGTH_SHORT).show();
            }
        }
    };
    private XlistViewAdapter viewAdapter;
    private Dao dao;
    private DataAdapter dataAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_a, null);
        xListView = view.findViewById(R.id.xlistview);

        dao = new Dao(getActivity());
        viewAdapter = new XlistViewAdapter(getActivity());
        dataAdapter = new DataAdapter(getActivity());

        //设置允许下拉
        xListView.setPullLoadEnable(true);
        //设置允许上拉
        xListView.setPullRefreshEnable(true);
        //设置监听
        xListView.setXListViewListener(this);
        newThread(1);
        //判断网络状态
        Boolean netWork = NetWork(getActivity());
        if(netWork==true){

            xListView.setAdapter(viewAdapter);
        }else{
            List<SqlBean> sqlBeans = dao.Query();
            dataAdapter.setList(sqlBeans);
            xListView.setAdapter(dataAdapter);
        }
        return view;
    }

    public Boolean NetWork(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info != null){
            return true;
        }else{
            return false;
        }
    }

    private void newThread(final int num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path+page);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置读取超时
                    connection.setReadTimeout(5000);
                    if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                        String data = CharStreams.toString(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                        Message message = new Message();
                        message.obj = data;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onRefresh() {
        page = 1;
        listAll.clear();
        newThread(1);
    }

    @Override
    public void onLoadMore() {
        page++;
        newThread(page);
    }

}
