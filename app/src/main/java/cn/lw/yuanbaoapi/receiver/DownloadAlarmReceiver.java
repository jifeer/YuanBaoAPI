package cn.lw.yuanbaoapi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.lw.yuanbaoapi.service.DownloadCoinService;

/**
 * Created by Administrator on 2017/7/16.
 */

public class DownloadAlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "DownloadAlarmReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newIntent = new Intent(Intent.ACTION_SYNC, null ,context, DownloadCoinService.class);
        context.startService(newIntent);
    }
}
