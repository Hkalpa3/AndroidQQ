package com.example.zed.androidqq.MyDB.MyDBBean;

/**
 * Created by zed on 2017/12/29.
 */

public class MyMsg {

    int msgID;
    String user1,user2;
    String content;
    String time;
    String type;

    public MyMsg(int msgID, String user1, String user2, String content, String time, String type) {
        this.msgID = msgID;
        this.user1 = user1;
        this.user2 = user2;
        this.content = content;
        this.time = time;
        this.type = type;
    }

    public MyMsg(){}

    public int getMsgID() {
        return msgID;
    }
    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
