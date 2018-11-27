package com.bwie.yuekaozhudong.Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "yuekao";

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table "
                +Dao.TABLE_NAME+" ("
                +Dao.BEAN_ID+" Integer primary key autoincrement , "
                +Dao.BEAN_CONTENT+" text , "
                +Dao.BEAN_ITEMDATA+" text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
