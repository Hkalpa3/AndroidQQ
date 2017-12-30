package com.example.zed.androidqq.MyDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zed.androidqq.MyDB.MyDBBean.MyMsg;
import com.example.zed.androidqq.db.dbBean.MsgDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zed on 2017/12/29.

 */

//update(String table, ContentValues values, String whereClause, String[] whereArgs)
//update weiboTb set title='heihiehiehieh' where id=2;

public class MyDbHelper {
    private SQLiteDatabase db;
    private MySqlHelper dbHelper;

    public MyDbHelper(Context context) {
        dbHelper = new MySqlHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    /*
    Cursor cursor = db.rawQuery("select name from *** where id=?", new String[]{"1"});
    Cursor cursor = db.query("***", new String[]{"name"}, "id=?", new String[]{"1"}, null, null, null);
    要区别是rawQuery是直接使用SQL语句进行查询的，也就是第一个参数字符串，在字符串内的“？”会被后面的String[]数组逐一对换掉；
    而query函数是Android自己封装的查询API：它的API文档如下：
     */

    /*
          msg_id ,
          msgUser1 nvarchar,
          msgUser2 nvarchar,
          content nvarchar,
          time varchar,
          type varchar";
     */

    /**插入一条信息
     * @param oneMsg
     * @return
     */
    public boolean insertOneMsg(MyMsg oneMsg){
//        try{
//            db.execSQL
//                    ("insert into MyMsg(msg_id,user1,user2,content,time,type) values(?,?,?,?,?,?,?)",
//                     new Object[]
//                      {oneMsg.getMsgID(), oneMsg.getUser1(), oneMsg.getUser2(), oneMsg.getContent(), oneMsg.getTime(), oneMsg.getType()});
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
        ContentValues cv=new ContentValues();
        cv.put("msg_id",oneMsg.getMsgID());
        cv.put("user1",oneMsg.getUser1());
        cv.put("user2",oneMsg.getUser2());
        cv.put("content",oneMsg.getContent());
        cv.put("time",oneMsg.getTime());
        cv.put("type",oneMsg.getType());
        if(db.insert("MyMsg",null,cv)!=-1){
            return true;
        }else
            return false;

    }


    /**获取 和某人的 聊天记录
     * @param user1
     * @param user2
     * @return 消息列表
     */
    public List<MyMsg> getMsgListWithSB(String user1,String user2) {
        List<MyMsg> list = new ArrayList<>();
        //String sql = "select * from MyMsg ";
        //Cursor cursor = db.rawQuery("select * from msg where msg_list_id=?", new String[]{msg_list_id + ""});
        Cursor cursor = db.rawQuery
                ("select * from MyMsg where (user1=? and user2=?)or(user1=? and user2=?)", new String[]{user1, user2, user2, user1});
        if (cursor.getCount()!=0) {
            while (cursor.moveToNext()) {
                int msg_id = cursor.getInt(cursor.getColumnIndex("msg_id"));
                String u1 = cursor.getString(cursor.getColumnIndex("msgUser1"));
                String u2 = cursor.getString(cursor.getColumnIndex("msgUser2"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String time = cursor.getColumnName(cursor.getColumnIndex("time"));
                String msgType = cursor.getColumnName(cursor.getColumnIndex("type"));
                MyMsg myMsg = new MyMsg(msg_id, u1, u2, content, time, msgType);
                list.add(myMsg);
            }
            cursor.close();
        }
        else {
            cursor.close();
            return null;
        }
        return list;
    }

}
