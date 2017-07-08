package cn.lw.yuanbaoapi.utils;

import android.util.Log;

import cn.lw.yuanbaoapi.BuildConfig;

/**
 * Created by lw on 2017/6/29.
 */
public class LogUtils {

    public static void LogE(String key, String value){
        if (BuildConfig.DEBUG){
            Log.e(key, value);
        }
    }

    public static void LogD(String key, String value){
        if (BuildConfig.DEBUG){
            Log.d(key, value);
        }
    }

    public static void LogV(String key, String value){
        if (BuildConfig.DEBUG){
            Log.v(key, value);
        }
    }

    public static void LogI(String key, String value){
        if (BuildConfig.DEBUG){
            Log.i(key, value);
        }
    }

}
