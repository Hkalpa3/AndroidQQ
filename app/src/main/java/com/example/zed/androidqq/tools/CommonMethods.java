package com.example.zed.androidqq.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zed on 2017/12/26.
 */

public class CommonMethods {


    private static DateFormat dateFormat=new SimpleDateFormat("yyyy MM dd HH:mm");

    //private static DateFormat timeFormat=new SimpleDateFormat("hh:mma");
    /*
    SimpleDateFormat函数语法：

      G 年代标志符
      y 年
      M 月
      d 日
      h 时 在上午或下午 (1~12)
      H 时 在一天中 (0~23)
      m 分
      s 秒
      S 毫秒
      E 星期
      D 一年中的第几天
      F 一月中第几个星期几
      w 一年中第几个星期
      W 一月中第几个星期
      a 上午 / 下午 标记符
      k 时 在一天中 (1~24)
      K 时 在上午或下午 (0~11)
      z 时区
     */
//    public static String getCurrentTime(){
//        Date today= Calendar.getInstance().getTime();
//        return timeFormat.format(today);
//    }

    public static String getCurrentDate(){
        Date today= Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

}
