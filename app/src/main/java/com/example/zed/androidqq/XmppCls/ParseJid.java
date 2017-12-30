package com.example.zed.androidqq.XmppCls;

import org.jxmpp.jid.EntityBareJid;

/**
 * Created by zed on 2017/12/26.
 */

public class ParseJid {
//    from=lm@kalpa3pc/Spark
    public static String parseMessageFromToString(EntityBareJid jid){
       String id;
       id=jid.toString().replace("@kalpa3pc","");
       //XmppStringUtils.completeJidFrom()
       return id;
    }

//    public static String getStringJid(){
//        //XmppStringUtils.parseBareJid()
//    }
}
