package com.example.zed.androidqq.Bean;

/**
 * Created by zed on 2017/12/26.
 * 聊天消息 实体
 */

public class ChatTextMsg{
    public static final int SELF_MSG = 1;//自己的消息
    public static final int FRIENDS_MSG = 2;//对方的消息
    
    private String from;
    private String body;
    private String to;
    private int whoSend;        //区分自己还是好友的消息
    private String stanzaId;

    public ChatTextMsg(String from, String body, String to, int whoSend, String stanzaId) {
        this.from = from;
        this.body = body;
        this.to = to;
        this.whoSend = whoSend;
        this.stanzaId = stanzaId;
    }

    public ChatTextMsg(String from, String body, String to, int whoSend) {
        this.from = from;
        this.body = body;
        this.to = to;
        this.whoSend = whoSend;
    }

    public ChatTextMsg(){}

    public String getFrom() {return from;
    }

    public void setFrom(String from) {this.from = from;
    }

    public String getBody() {return body;
    }

    public void setBody(String body) {this.body = body;
    }

    public String getTo() {return to;
    }

    public void setTo(String to) {this.to = to;
    }

    public int getWhoSend() {return whoSend;
    }

    public void setWhoSend(int whoSend) {this.whoSend = whoSend;
    }

    public String getStanzaId() {return stanzaId;
    }

    public void setStanzaId(String stanzaId) {this.stanzaId = stanzaId;
    }
}
