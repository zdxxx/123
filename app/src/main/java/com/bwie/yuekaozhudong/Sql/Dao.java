package com.bwie.yuekaozhudong.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    public static final String TABLE_NAME = "item";
    public static final String BEAN_ID = "id";
    public static final String BEAN_CONTENT = "content";
    public static final String BEAN_ITEMDATA = "itemdata";
    private final SQLiteDatabase database;

    public Dao(Context context){
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        database = myOpenHelper.getReadableDatabase();
    }

    public long Add(SqlBean bean){
        ContentValues values = new ContentValues();
        values.put(BEAN_CONTENT,bean.getContent());
        values.put(BEAN_ITEMDATA,bean.getItemdata());
        long insert = database.insert(TABLE_NAME, null, values);
        return insert;
    }

    public List<SqlBean> Query(){
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);
        List<SqlBean> list = new ArrayList<>();
        while (cursor.moveToNext()){
            int sid = cursor.getInt(cursor.getColumnIndex(BEAN_ID));
            String scontent = cursor.getString(cursor.getColumnIndex(BEAN_CONTENT));
            String sitemdata = cursor.getString(cursor.getColumnIndex(BEAN_ITEMDATA));
            SqlBean bean = new SqlBean(sid, scontent, sitemdata);
            list.add(bean);
        }
        return list;
    }
}
