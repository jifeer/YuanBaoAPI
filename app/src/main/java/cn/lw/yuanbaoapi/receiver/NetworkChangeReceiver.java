package cn.lw.yuanbaoapi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import cn.lw.yuanbaoapi.commons.net.NetType;
import cn.lw.yuanbaoapi.utils.NetUtils;

/**
 * Created by Administrator on 2017/7/10.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    private static boolean isConnected = false;
    private static final String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static List<NetChangeObserver> lists = new ArrayList<>();
    private NetType netType;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) return;
        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION)){
            if (NetUtils.isConnected(context)){
                isConnected = true;
                netType = NetUtils.getNetType(context);
            }else{
                isConnected = false;
            }
            notifyObserver();
        }
    }

    /**
     * 注册网络状态观察者
     * @param observer
     */
    public static void registerNetStateObserver(NetChangeObserver observer){
        if (lists == null){
            lists = new ArrayList<>();
        }
        lists.add(observer);
    }

    /**
     * 解除网络状态改变观察者
     * @param observer
     */
    public static void unregisterNetStateObserver(NetChangeObserver observer){
        if (lists != null) {
            lists.remove(observer);
        }
    }

    /**
     * 网络状态改变,向所有观察者发送消息
     */
    private void notifyObserver(){
        if (lists != null && lists.size() > 0){
            for (NetChangeObserver observer: lists) {
                if (observer != null){
                    if (isConnected){
                        observer.onConnect(netType);
                    }else {
                        observer.disConnect();
                    }
                }
            }
        }
    }

    public interface NetChangeObserver {
        public void onConnect(NetType netType);

        public void disConnect();
    }
}
