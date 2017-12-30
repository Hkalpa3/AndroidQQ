package com.example.zed.androidqq.XmppCls;

import android.util.Log;

import com.example.zed.androidqq.Bean.ChatTextMsg;
import com.example.zed.androidqq.RxBus.RxBus;

import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;

/**
 * Created by zed on 2017/12/26.
 */

public class MyIncomingMsgListener implements IncomingChatMessageListener  {


    @Override
    public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
        Log.i("msg", "newIncomingMessage: "+message.getBody());
        Log.i("msg", "newIncomingMessage: msg::"+message.toString());
        Log.i("msg", "newIncomingMessage: msg::"+message.toXML());

        ChatTextMsg msg=new ChatTextMsg();
        msg.setBody(message.getBody());
        msg.setFrom(ParseJid.parseMessageFromToString(from));
        msg.setWhoSend(ChatTextMsg.FRIENDS_MSG);
        msg.setStanzaId(message.getStanzaId());
        //msg.setTo(ParseJid.parseMessageFromToString(message.getTo());
        //Log.i("m", "newIncomingMessage: "+from.toString()+from.asEntityBareJidString()+from.asEntityBareJid()+from.intern());
        //Log.i("m", "newIncomingMessage:"+ "JidUtil.class.getCanonicalName()::"+JidUtil.class.getCanonicalName()+ "Jid.class.getName()"+Jid.class.getName()+Jid.class.toString()+Jid.class.getSimpleName());

        RxBus.getInstance().post(msg);


    }

}
