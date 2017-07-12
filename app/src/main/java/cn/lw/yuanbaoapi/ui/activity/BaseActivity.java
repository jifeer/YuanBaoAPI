package cn.lw.yuanbaoapi.ui.activity;

import android.support.v7.app.AppCompatActivity;

import cn.lw.yuanbaoapi.commons.net.NetType;
import cn.lw.yuanbaoapi.receiver.NetworkChangeReceiver;
import cn.lw.yuanbaoapi.utils.LogUtils;

public class BaseActivity extends AppCompatActivity implements NetworkChangeReceiver.NetChangeObserver{
    private static final String TAG = "BaseActivity";

    @Override
    protected void onResume() {
        super.onResume();
        NetworkChangeReceiver.registerNetStateObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NetworkChangeReceiver.unregisterNetStateObserver(this);
    }

    //网络状态改变重载方法
    @Override
    public void onConnect(NetType netType) {
        LogUtils.LogD(TAG, "onConnect");
    }

    @Override
    public void disConnect() {
        LogUtils.LogD(TAG, "disconnect");
    }
}
