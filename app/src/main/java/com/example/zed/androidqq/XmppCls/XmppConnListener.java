package com.example.zed.androidqq.XmppCls;

import android.util.Log;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zed on 2017/12/13.
 */

public class XmppConnListener implements ConnectionListener {
    private String acc,pwd;
    private Timer timerExit;
    private int loginTime=2000;

    public XmppConnListener(String acc,String pwd){
        super();
        this.acc=acc;
        this.pwd=pwd;

    }
    @Override
    public void connected(XMPPConnection connection) {
        Log.i("conlistener", "connected: ");
    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {
        Log.i("conlistener", "authenticated: "+resumed);
    }

    @Override
    public void connectionClosed() {
        Log.i("conlistener", "连接关闭");
        // 关闭连接
        XmppConnection.getInstance().disConnect();
        // 重连服务器
        timerExit = new Timer();
        timerExit.schedule(new TimeTask(),loginTime);
    }
    private class TimeTask extends TimerTask {
        @Override
        public void run() {
            if (acc != null && pwd != null) {
                Log.i("conlistener", "尝试登录");
                // 连接服务器
                try {
                    if (!XmppConnection.getInstance().isAuthenticated()) {// 用户未登录
                        if (XmppConnection.getInstance().login(acc, pwd)) {
                            Log.i("conlistener", "登录成功");
                        } else {
                            Log.i("conlistener", "重新登录");
                            timerExit.schedule(new TimeTask(), loginTime);
                        }
                    }
                } catch (Exception e) {
                    Log.i("conlistener", "尝试登录,出现异常!");
                }
            }
        }
    }

    @Override
    public void connectionClosedOnError(Exception e) {
        Log.i("conlistener", "连接关闭异常");
        // 判断账号已被登录
        boolean error = e.getMessage().equals("stream:error (conflict)");
        if (!error) {
            // 关闭连接
            XmppConnection.getInstance().disConnect();
            // 重连服务器
            timerExit = new Timer();
            timerExit.schedule(new TimeTask(), loginTime);
        } else {
            // 退出登录
        }
    }

    
    @Override
    public void reconnectionSuccessful() {
        Log.i("conlistener", "reconnectionSuccessful");
    }

    @Override
    public void reconnectingIn(int seconds) {
        Log.i("conlistener", "reconnectingIn" + seconds);
    }

    @Override
    public void reconnectionFailed(Exception e) {
        Log.i("conlistener", "reconnectionFailed" + e.getMessage());
    }
}
