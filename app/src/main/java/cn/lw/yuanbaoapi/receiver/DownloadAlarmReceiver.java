package cn.lw.yuanbaoapi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/7/16.
 */

public class DownloadAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "AlarmManager设置成功", Toast.LENGTH_SHORT).show();
    }
}
