package cn.lw.yuanbaoapi.ui.activity;

import android.support.v7.app.AppCompatActivity;

import cn.lw.yuanbaoapi.commons.net.NetType;
import cn.lw.yuanbaoapi.receiver.NetworkChangeReceiver;

public class BaseActivity extends AppCompatActivity implements NetworkChangeReceiver.NetChangeObserver{

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

    }

    @Override
    public void disConnect() {

    }
}
