package com.example.zed.androidqq.Bean;

import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.jxmpp.jid.Jid;

/**
 * Created by zed on 2017/12/14.
 *
 */
/**
 *      ItemType:
 * The user does not have a subscription to the contact's presence, and the contact does not
 * have a subscription to the user's presence; this is the default value, so if the
 * subscription attribute is not included then the state is to be understood as "none".
 */
//        none('⊥'),
                /**
                 * The user has a subscription to the contact's presence, but the contact does not have a
                 * subscription to the user's presence.
                 */
//                to('←'),
                /**
                 * The contact has a subscription to the user's presence, but the user does not have a
                 * subscription to the contact's presence.
                 */
//                from('→'),
                /**
                 * The user and the contact have subscriptions to each other's presence (also called a
                 * "mutual subscription").
                 */
//                both('↔'),
                /**
                 * The user wishes to stop receiving presence updates from the subscriber.
                 */
//                remove('⚡'),

public class User {

    private Jid jid;
    String name;    //用户名
    RosterPacket.ItemType type;
    String group;
//    String nicknime;


    public User(){}
    public User(Jid id, String name, RosterPacket.ItemType type, String group) {
        this.jid = id;
        this.name = name;
        this.type = type;
        this.group = group;
    }

    public  void setJId(Jid jid){this.jid=jid;}
    public Jid getJId() {
        return jid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public RosterPacket.ItemType getType() {
        return type;
    }
    public void setType(RosterPacket.ItemType type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }


}
