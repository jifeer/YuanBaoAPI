package cn.lw.yuanbaoapi.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/7/15.
 */

public class DeviceUtils {

    /**
     * adb命令获得mac地址
     * @return    String类型mac地址
     */
    public static String getDeviceId() {
        String result = "";
        String Mac = "";
        result = callCmd("ip address show", "ether");

        if (result == null) {
            //获取wifimac地址失败
            return "";
        } else {
            String[] str = result.split(" ");
            String ipmac = str[5];
            return ipmac;
        }
    }
    private static String callCmd(String cmd, String filter) {
        String result = "";
        String line = "";
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);

            //执行命令cmd，只取结果中含有filter的这一行
            while ((line = br.readLine()) != null && line.contains(filter) == false) {
                //result += line;
                Log.i("test", "line: " + line);
            }

            result = line;
            Log.i("test", "result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getIP(Context ct) {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) ct.getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    /**
     * 获取设备信息
     * @return
     */
    public static String getPhoneProductAndModel(){
        Build build = new Build();
        return Build.MODEL;
    }

    /**
     * 格式转换
     *
     * @param i
     * @return
     */
    public static String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    public static void startClass(Context mContext,Class cls){
        if(cls==null) return;
        Intent it=new Intent(mContext,cls);
        mContext.startActivity(it);
    }

    //获取apk版本号
    public static int getVersionCode(Context context)
    {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int versionCode = packInfo.versionCode;
            return versionCode;
        }catch (PackageManager.NameNotFoundException e){
            return 0;
        }
    }

    /**
     * dp转px
     *
     * @return
     */
    public static int dp2px(Context ct, float dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, ct.getResources().getDisplayMetrics());
    }
}
