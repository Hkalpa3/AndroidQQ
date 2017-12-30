package com.example.zed.androidqq.db.dbBean;

/**
 * Created by zed on 2017/12/27.
 */

public class MsgDB {
    public static final int SELF_MSG = 1;//自己的消息
    public static final int FRIENDS_MSG = 2;//对方的消息
    private int msg_id;
    private int msg_list_id;
    private String from_name;
    private String msg_content;
    private String msg_time;
    private String msg_type;
    private int from_type;

    public MsgDB(int msg_id, int msg_list_id, String from_name, String msg_content, String msg_time, String msg_type, int from_type) {
        this.msg_id = msg_id;
        this.msg_list_id = msg_list_id;
        this.from_name = from_name;
        this.msg_content = msg_content;
        this.msg_time = msg_time;
        this.msg_type = msg_type;
        this.from_type = from_type;
    }

    public static int getSelfMsg() {
        return SELF_MSG;
    }

    public static int getFriendsMsg() {
        return FRIENDS_MSG;
    }

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public int getMsg_list_id() {
        return msg_list_id;
    }

    public void setMsg_list_id(int msg_list_id) {
        this.msg_list_id = msg_list_id;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public int getFrom_type() {
        return from_type;
    }

    public void setFrom_type(int from_type) {
        this.from_type = from_type;
    }

    public MsgDB(int msg_list_id, String from_name, String msg_content, String msg_time, String msg_type, int from_type) {

        this.msg_list_id = msg_list_id;
        this.from_name = from_name;
        this.msg_content = msg_content;
        this.msg_time = msg_time;
        this.msg_type = msg_type;
        this.from_type = from_type;
    }
}
