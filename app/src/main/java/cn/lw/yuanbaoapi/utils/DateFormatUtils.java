package cn.lw.yuanbaoapi.utils;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/7/19.
 */

public class DateFormatUtils {

    public static String getDate(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(time);
    }
}
