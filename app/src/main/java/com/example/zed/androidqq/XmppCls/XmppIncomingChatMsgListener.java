package com.example.zed.androidqq.XmppCls;

import android.util.Log;

import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;

/**
 * Created by zed on 2017/12/20.
 */

public class XmppIncomingChatMsgListener implements IncomingChatMessageListener,MessageListener {

    @Override
    public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
        Log.i("msg", "newIncomingMessage: "+message.getBody());

    }


    @Override
    public void processMessage(Message message) {

    }
}
