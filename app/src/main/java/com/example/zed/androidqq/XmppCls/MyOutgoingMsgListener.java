package com.example.zed.androidqq.XmppCls;

import android.util.Log;

import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.OutgoingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;

/**
 * Created by zed on 2017/12/26.
 */

public class MyOutgoingMsgListener implements OutgoingChatMessageListener {
    @Override
    public void newOutgoingMessage(EntityBareJid to, Message message, Chat chat) {
        Log.i("msg", "newOutgoingMessage: toXML:"+message.toXML()+"--toString:"+message.toString());
    }
}
