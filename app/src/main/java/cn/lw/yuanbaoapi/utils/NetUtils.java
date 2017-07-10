package cn.lw.yuanbaoapi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cn.lw.yuanbaoapi.commons.net.NetType;

/**
 * Created by Administrator on 2017/7/10.
 */

public class NetUtils {
    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context)
    {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity)
        {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected())
            {
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取网络类型
     * @param context
     * @return
     */
    public static NetType getNetType(Context context){
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_MOBILE){
            return NetType.MOBIE;
        }else if(cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI){
            return NetType.WIFI;
        }else {
            return NetType.NONET;
        }
    }
}
