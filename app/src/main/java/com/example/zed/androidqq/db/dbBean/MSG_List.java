package com.example.zed.androidqq.db.dbBean;

/**
 * Created by zed on 2017/12/27.
 */

public class MSG_List {
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getLast_msg() {
        return last_msg;
    }

    public void setLast_msg(String last_msg) {
        this.last_msg = last_msg;
    }

    public String getLast_img_time() {
        return last_img_time;
    }

    public void setLast_img_time(String last_img_time) {
        this.last_img_time = last_img_time;
    }

    public int getMsg_list_type() {
        return msg_list_type;
    }

    public void setMsg_list_type(int msg_list_type) {
        this.msg_list_type = msg_list_type;
    }

    public int getMsg_list_id() {
        return msg_list_id;
    }

    public void setMsg_list_id(int msg_list_id) {
        this.msg_list_id = msg_list_id;
    }

    int msg_list_id;
    int user_id;
    String to_name;
    String last_msg;
    String last_img_time;
    int msg_list_type;
    public MSG_List(){}

    public MSG_List(int msg_list_id, int user_id, String to_name, String last_msg, String last_img_time, int msg_list_type) {
        this.msg_list_id = msg_list_id;
        this.user_id = user_id;
        this.to_name = to_name;
        this.last_msg = last_msg;
        this.last_img_time = last_img_time;
        this.msg_list_type = msg_list_type;
    }
}
