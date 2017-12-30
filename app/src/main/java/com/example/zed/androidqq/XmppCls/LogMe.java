package com.example.zed.androidqq.XmppCls;

import android.util.Log;

/**
 * Created by zed on 2017/12/28.
 */

public class LogMe  {
    public static void logI(String par1,String par2,String par3){
        Log.i("msg", "logI:   "+par1+"==========="+par2+" ==========="+par3);
    }
    public static void logI(String par1,String par2){
        Log.i("msg", "logI:   :"+par1+"==========="+par2);
    }
    public static void logI(String par1){
        Log.i("msg", "logI:   :"+par1);
    }
}
