package com.example.zed.androidqq.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zed on 2017/12/27.
 */

public class SqLiteHelper extends SQLiteOpenHelper {

    public SqLiteHelper(Context context) {
        super(context, "ofdb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // 用户表：     id      user_name    user_psd
        String createUser="create table user(user_id  INTEGER primary key autoincrement,user_name varchar,user_psd varchar)";
        //消息列表表：  id      userFrom      to_name     last_msg        last_msg_time   msg_list_type
        String createMy_Msg_List="create table msg_list(msg_list_id INTEGER primary key autoincrement,userFrom varchar,userTo varchar,last_msg varchar,last_msg_time varchar,msg_list_type)";
        //消息 表：
        String createMsg="create table msg(msg_id INTEGER primary key autoincrement,msg_list_id  INTEGER,from_name varchar,msg_content varchar,msg_time varchar,msg_type varchar ,from_type INTEGER)";

        db.execSQL(createUser);

        db.execSQL(createMy_Msg_List);

        db.execSQL(createMsg);
//        db.execSQL("create table msg_list(msg_list_id  INTEGER primary key autoincrement,user_id INTEGER,to_name varchar,last_msg varchar,last_msg_time varchar,msg_list_type)");
//        db.execSQL("create table msg_list(msg_list_id  INTEGER primary key autoincrement,user_id INTEGER,to_name varchar,last_image varchar,last_img_time varchar,msg_list_type, foreign key (user_id) references  user(user_id) on delete cascade on update cascade )");
//        db.execSQL("create table msg(msg_id INTEGER primary key autoincrement,msg_list_id  INTEGER,from_name varchar,msg_content varchar,msg_time varchar,msg_type varchar, foreign key (msg_list_id) references  msg_list(msg_list_id) on delete cascade on update cascade )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
