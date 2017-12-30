package com.example.zed.androidqq.XmppCls;

import com.example.zed.androidqq.Bean.User;
import com.example.zed.androidqq.MyDB.MyDbHelper;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterGroup;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.offline.OfflineMessageManager;
import org.json.JSONObject;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.parts.Localpart;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zed on 2017/12/7.
 */


// extends Service
public class XmppConnection{

    //单例模式
    private static XmppConnection xmppConnectionclass = new XmppConnection();
    //连接监听
    private XmppConnListener conListener;

    private ChatManager chatManager;
    //单聊接受消息 监听对象       在登录时监听  断开连接移除监听
    MyIncomingMsgListener myIncomingMsgListener;
    //发送消息 监听
    MyOutgoingMsgListener myOutgoingMsgListener;
//    //单聊接受消息 监听对象       在登录时监听  断开连接移除监听
//    IncomingChatMessageListener incomingChatMessageListener;
//    //发送消息 监听
//    OutgoingChatMessageListener outgoingMsgListener;
    private MyDbHelper dbHelper;


//    public class LocalBinder extends Binder {
//        public XmppConnection getService() {
//            return XmppConnection.this;
//        }
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//    private final IBinder mBinder = new LocalBinder();
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        dbHelper = new MyDbHelper(this);
//        return super.onStartCommand(intent, flags, startId);
//    }

    public synchronized static XmppConnection getInstance() {
        if (xmppConnectionclass == null) {
            xmppConnectionclass = new XmppConnection();
            return xmppConnectionclass;
        }
        return xmppConnectionclass;
    }

    private static int mPort = 5222;
    private static String mHOST = "10.0.2.2";
//    private static String mHOST = "192.168.1.100";
    private static String mNAME = "kalpa3pc";

    private AbstractXMPPConnection connection = null;

  //  private static Roster roster=Roster.getInstanceFor(getInstance().connection);


    /**获取当前 连接对象 JID
     * @return
     */
    public EntityBareJid getCurrentUserJid(){
       return getConnection().getUser().asEntityBareJid();
    }

    /**
     * 创建连接
     *
     * @return
     */
    public boolean createConnection() {
        try {
            XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
                    .setHostAddress(InetAddress.getByName(mHOST))
                    .setPort(mPort)
                    .setXmppDomain(mNAME)
                    //关闭安全模式
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    //压缩模式
                    .setCompressionEnabled(true)
                    //离线状态
                    //If the presence is false or not set,
                    // then all your incoming messages will be saved in your Offline message storage and
                    // the interface will not be triggered.
                    .setSendPresence(false)
                    //开启调试模式
                    .setDebuggerEnabled(true)
                    .build();

//            // 将相应机制隐掉
//            SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-1");
//            SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");

            //需要经过同意才可以添加好友
            Roster.setDefaultSubscriptionMode(Roster.SubscriptionMode.manual);
            connection = new XMPPTCPConnection(configuration);
            connection.connect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            connection = null;
        }
        return false;
    }



    /**
     * 获取连接对象
     * 开启新线程 （访问网络）
     *
     * @return
     */
    public AbstractXMPPConnection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    //获取chatManager
    public ChatManager getChatManager(){
        if(chatManager == null){
            chatManager = ChatManager.getInstanceFor(getConnection());
        }
        return chatManager;
    }


    public boolean login(String acc, String pwd) {
        try {
            getConnection().login(acc, pwd);
//            //更改为 离线
//            setPresence(0);
            //添加监听
            conListener =new XmppConnListener(acc, pwd);
            getConnection().addConnectionListener(conListener);
//            //接受消息监听  到主界面 监听
//            addChatListener();

//            设置为在线状态
            Presence p=new Presence(Presence.Type.available);
            connection.sendStanza(p);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 开始消息监听
     */
    public  void addChatListener(){
        //ChatManager chatManager=ChatManager.getInstanceFor(getConnection());
        myIncomingMsgListener=new MyIncomingMsgListener();
        myOutgoingMsgListener=new MyOutgoingMsgListener();
        //ChatManager.addIncomingListener()

        getChatManager().addIncomingListener(myIncomingMsgListener);
        getChatManager().addOutgoingListener(myOutgoingMsgListener);
    }

//    public void addChatMessageLsner(){
//        ChatMessageListener chatMessageListener=new ChatMessageListener() {
//            @Override
//            public void processMessage(Chat chat, Message message) {
//
//            }
//        }
//    }

    /**
     * 移除消息监听
     */
    public void removeListener(){
        getChatManager().removeListener(myOutgoingMsgListener);
        getChatManager().removeListener(myIncomingMsgListener);
    }

    /**
     * 关闭连接
     */
   public void disConnect() {
       if (connection != null) {
           connection.removeConnectionListener(conListener);
           //移除聊天监听
          removeListener();
           if(connection.isConnected()){
               connection.disconnect();
           }
       }
   }

    /**注册
     * @param acc
     * @param pwd
     * attrs 必须初始化为空  否则 不能注册
     * @return
     */
    public boolean register(String acc,String pwd){
        if (connection==null)
            return false;
        try {
            AccountManager.sensitiveOperationOverInsecureConnectionDefault(true);

            AccountManager manager = AccountManager.getInstance(connection);

            Map<String,String> attrs=new HashMap<>();
            manager.getInstance(connection).createAccount(Localpart.from(acc), pwd,attrs);
                return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**验证是否 已登录
     * @return
     */
    public boolean isAuthenticated() {
        return connection != null && connection.isConnected() && connection.isAuthenticated();
    }

    private void setPresence(int code){

    }

    /**
     * 一上线获取离线消息
     * 设置登录状态为在线
     */
    private void getOfflineMessage() {
        OfflineMessageManager offlineManager = new OfflineMessageManager(connection);
        try {
            List<Message> list = offlineManager.getMessages();
            for (Message message : list) {
                //message.setFrom(String.valueOf(message.getFrom()).split("/")[0]);
                message.setFrom(message.getFrom().toString().split("/")[0]);

                JSONObject object = new JSONObject(message.getBody());
                String type = object.getString("type");
                String data = object.getString("data");
//                //保存离线信息
//                dbHelper.insertOneMsg(user.getUser_id(), message.getFrom(), data, System.currentTimeMillis() + "", message.getFrom(), 2);
            }
            //删除离线消息
            offlineManager.deleteMessages();
            //将状态设置成在线
            Presence presence = new Presence(Presence.Type.available);
            connection.sendStanza(presence);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**获取 Roster 对象
     * @return
     */
    public Roster getRoster(){
        return Roster.getInstanceFor(getConnection());
    }

    /**获取所有分组信息
     * @return getGroups()
     * 返回Collection<RosterGroup>
     */
    public List<String>getAllGroupName(){
        if(connection==null)
            return null;
        List<String>list=new ArrayList<>();

        //所有 组名
        Collection<RosterGroup>groupCollection= getRoster().getGroups();
        for (RosterGroup data: groupCollection) {
            list.add(data.getName());
        }
        return list;
    }

    /**
     * @param groupName 组名
     * @return 返回User List
     * RosterGroup
     * 通过RosterEntry 访问每一个用户的具体信息
     */
    public List<User>getUsersByGroupName(String groupName){
        if(connection==null)
            return null;
        List<User>list=new ArrayList<>();
        //返回 组
        RosterGroup rosterGroup=getRoster().getGroup(groupName);
        //遍历
        List<RosterEntry>entries= rosterGroup.getEntries();
        for (RosterEntry entry:entries) {
                User user=new User();
                user.setJId(entry.getJid());    //BareJID
                user.setGroup(groupName);       //组名
                user.setType(entry.getType());  //订阅状态
                user.setName(entry.getName());  //用户名

                list.add(user);
            // getRoster().createEntry();
           // LogMe.logI(entry.getName(),entry.getGroups().toString(),entry.getType().toString());
        }


        return list;

    }

    /**根据 jid获取用户
     * @param id
     * @return User实体
     */
    public User getUserBy(Jid id){
        Collection<RosterGroup>groups=getRoster().getGroups();
        User user=new User();
        //按组 遍历
        for (RosterGroup group: groups) {
            if(group.getEntry(id)!=null){  //直接根据  jid查找用户 withGroup

                RosterEntry entry=group.getEntry(id);
                user.setJId(entry.getJid());
                user.setName(entry.getName());
                user.setType(entry.getType());
                user.setGroup(group.getName());
                break;
            }
        }

        return user;
//
//        Set<RosterEntry>entries= roster.getEntries();
//        for (RosterEntry entry : entries) {
//            if(entry.getJid()==id){
//                User user=new User();
//                user.setId(id);
//                user.setName(entry.getName());
//                user.setType(entry.getType());
//                user.setGroup();
//            }else continue;
//        }
    }

    /**
     * @param name
     * @return
     */
    public User getUserBy(String name){
        return null;
    }

    /**添加新分组
     * @param groupName
     * @return
     */
    public boolean addGroup(String groupName){
        try {
            Roster.getInstanceFor(XmppConnection.getInstance().getConnection()).createGroup(groupName);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /** 重命名分组
     * @param oldName 原名称
     * @param newName
     * @return
     */
    public boolean renameGroup(String oldName,String newName){
        try {
            getRoster().getGroup(oldName).setName(newName);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
