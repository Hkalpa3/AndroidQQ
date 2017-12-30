package com.example.zed.androidqq.MyDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zed on 2017/12/29.
 */

public class MySqlHelper extends SQLiteOpenHelper {
    public MySqlHelper(Context context) {
        super(context, "MyMsgDB.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //消息表
        String CreateMsgTable=
                "CREATE TABLE IF NOT EXISTS MyMsg(msg_id INTEGER primary key autoincrement,msgUser1 nvarchar,msgUser2 nvarchar,content nvarchar,time varchar,type varchar";
        db.execSQL(CreateMsgTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
