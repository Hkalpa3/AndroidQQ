package com.example.zed.androidqq.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zed.androidqq.db.dbBean.MSG_List;
import com.example.zed.androidqq.db.dbBean.MsgDB;
import com.example.zed.androidqq.db.dbBean.UserDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zed on 2017/12/27.
 */

public class DbHelper {


    private SQLiteDatabase db;
    private SqLiteHelper dbHelper;

    public DbHelper(Context context) {
        dbHelper = new SqLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void Close() {
        db.close();
        dbHelper.close();
    }

    /**
     * 根据用户名获取用户id
     *
     * @param userName
     * @return
     */
    private UserDB GetUser(String userName) {
        UserDB user = null;
        Cursor cursor = db.rawQuery("select * from user where user_name=?", new String[]{userName});
        if (cursor.moveToNext()) {
            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
            String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
            String user_psd = cursor.getString(cursor.getColumnIndex("user_psd"));
           // String user_head_img = cursor.getString(cursor.getColumnIndex("user_head_img"));
            user = new UserDB(user_id, user_name, user_psd);
        }
        return user;
    }

    /**
     * 登录后插入用户
     *
     * @param userName
     * @param userPsd
     * @return
     */

//    public UserDB SetUserDB(String userName, String userPsd) {
//        UserDB user = GetUserDB(userName);
//        if (user == null) {
//            ContentValues values = new ContentValues();
//            values.put("user_name", userName);
//            values.put("user_psd", userPsd);
//            values.put("user_head_img", "");
//            db.insert("user", null, values);
//            user = GetUserDB(userName);
//            return user;
//        } else {
//            return user;
//        }
//    }

    /**
     * 获取聊天列表
     *
     * @param userId
     * @return
     */
    public List<MSG_List> getMsgAllList(int userId) {
        List<MSG_List> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from msg_list where user_id=?", new String[]{userId + ""});
        while (cursor.moveToNext()) {
            int msg_list_id = cursor.getInt(cursor.getColumnIndex("msg_list_id"));
            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
            String to_name = cursor.getString(cursor.getColumnIndex("to_name"));
            String last_msg = cursor.getString(cursor.getColumnIndex("last_msg"));
            String last_msg_time = cursor.getString(cursor.getColumnIndex("last_msg_time"));
            int msg_list_type = cursor.getInt(cursor.getColumnIndex("msg_list_type"));
            MSG_List msgList = new MSG_List(msg_list_id, user_id, to_name, last_msg, last_msg_time, msg_list_type);
            list.add(msgList);
        }
        //
        cursor.close();
        return list;
    }

    /**
     * 获取对应的人的聊天列表
     *
     * @param userId
     * @return
     */
    public MSG_List getMSG_List(int userId, String toName) {
        MSG_List msgList = null;
        try {
            Cursor cursor = db.rawQuery("select * from msg_list where user_id=? and to_name=? ", new String[]{userId + "", toName});
            while (cursor.moveToNext()) {
                int msg_list_id = cursor.getInt(cursor.getColumnIndex("msg_list_id"));
                int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
                String to_name = cursor.getString(cursor.getColumnIndex("to_name"));
                String last_msg = cursor.getString(cursor.getColumnIndex("last_msg"));
                String last_msg_time = cursor.getString(cursor.getColumnIndex("last_msg_time"));
                int msg_list_type = cursor.getInt(cursor.getColumnIndex("msg_list_type"));
                msgList = new MSG_List(msg_list_id, user_id, to_name, last_msg, last_msg_time, msg_list_type);
            }
        } catch (Exception e) {
            Log.e("error", "getMSG_List: " + e.toString());
        }

        return msgList;
    }

    /**
     * 插入一条和某某聊天的消息list
     *
     * @param userId
     * @param to_name
     * @param last_msg
     * @param last_msg_time
     * @param msg_list_type
     */
    private MSG_List insertMSG_List(int userId, String to_name, String last_msg, String last_msg_time, int msg_list_type) {
        db.execSQL("insert into msg_list(user_id,to_name,last_msg,last_msg_time,msg_list_type) values(?,?,?,?,?)",
                new Object[]{userId, to_name, last_msg, last_msg_time, msg_list_type});
        MSG_List msgList = getMSG_List(userId, to_name);
        return msgList;
    }

    /**
     * 检查是否有和对应人聊天的msg_list_id
     *
     * @param userId
     * @param toName
     * @return
     */
    public MSG_List checkMSG_List(int userId, String toName) {
        MSG_List msgList = null;
        msgList = getMSG_List(userId, toName);
        if (msgList == null) {
            insertMSG_List(userId, toName, "", "", 1);
            msgList = getMSG_List(userId, toName);
        }
        return msgList;
    }

    /**
     * 更新消息列表表中最后一条消息，用户消息列表显示
     *
     * @param userId
     * @param msg_list_id
     * @param last_msg
     * @param last_msg_time
     */
    private void updateMSG_List(int userId, int msg_list_id, String last_msg, String last_msg_time) {
//        db.execSQL("update msg_list where user_id=? and to_name=? set last_msg=? and last_msg_time=?", new Object[]{userId});

        ContentValues values = new ContentValues();
        values.put("last_msg", last_msg);
        values.put("last_msg_time", last_msg_time);
        db.update("msg_list", values, "user_id=? and msg_list_id=?", new String[]{userId + "", msg_list_id + ""});
    }

    /**
     * 插入消息
     *
     * @param msg_list_id
     * @param from_name
     * @param msg_content
     * @param msg_time
     * @param msg_type
     */
    private void insertMsg(int msg_list_id, String from_name, String msg_content, String msg_time, String msg_type, int from_type) {
        ContentValues values = new ContentValues();
        values.put("msg_list_id", msg_list_id);
        values.put("from_name", from_name);
        values.put("msg_content", msg_content);
        values.put("msg_time", msg_time);
        values.put("msg_type", msg_type);
        values.put("from_type", from_type);
        db.insert("msg", null, values);
    }

    /**
     * 插入一条消息消息
     *
     * @param user_id
     * @param to_name
     * @param msg_content
     * @param msg_time
     */
    public void insertOneMsg(int user_id, String to_name, String msg_content, String msg_time, String send_name, int from_type) {
        MSG_List msgList = null;
        msgList = getMSG_List(user_id, to_name);
        if (msgList == null) {
            msgList = insertMSG_List(user_id, to_name, msg_content, msg_time, from_type);
        } else {
            updateMSG_List(user_id, msgList.getMsg_list_id(), msg_content, msg_time);
        }
        insertMsg(msgList.getMsg_list_id(), send_name, msg_content, msg_time, "text", from_type);
    }

    /**
     * 获取和某某聊天的消息
     *
     * @param msg_list_id
     * @param page
     */
    public List<MsgDB> getAllMsgDB(int msg_list_id, int page) {
        List<MsgDB> list = new ArrayList<>();
        List<MsgDB> resultList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from msg where msg_list_id=?", new String[]{msg_list_id + ""});
        while (cursor.moveToNext()) {
            int msg_id = cursor.getInt(cursor.getColumnIndex("msg_id"));
            String from_name = cursor.getString(cursor.getColumnIndex("from_name"));
            String msg_content = cursor.getString(cursor.getColumnIndex("msg_content"));
            String msg_time = cursor.getString(cursor.getColumnIndex("msg_time"));
            String msg_type = cursor.getString(cursor.getColumnIndex("msg_type"));
            int from_type = cursor.getInt(cursor.getColumnIndex("from_type"));
            MsgDB msg = new MsgDB(msg_id, msg_list_id, from_name, msg_content, msg_time, msg_type, from_type);
            list.add(msg);
        }
//        for (int i = 10 * (page - 1); i < 10 * (page); i++) {
//            if (i >= list.size()) {
//                break;
//            }
//            resultList.add(list.get(i));
//        }
        return list;
    }

}
