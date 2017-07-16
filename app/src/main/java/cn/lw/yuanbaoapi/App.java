package cn.lw.yuanbaoapi;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;

import com.squareup.leakcanary.LeakCanary;

import cn.lw.yuanbaoapi.receiver.DownloadAlarmReceiver;

/**
 * Created by lw on 2017/6/28.
 */
public class App extends Application {
    private static App instance;
    public static AlarmManager manager;
    private PendingIntent pendingIntent;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLeanCanary();
        initDownloadAlarmManager();
    }

    private void initDownloadAlarmManager(){
        Intent alarmIntent = new Intent(this, DownloadAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int DOWN_INTERNAL = 30 * 60 * 1000;
        manager.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis(), DOWN_INTERNAL, pendingIntent);
    }


    //内存泄漏监控初始化
    private void initLeanCanary(){
        if (BuildConfig.DEBUG){
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }
    }

    public static App getApplication(){
        return instance;
    }

    public void appExit(){
        System.gc();
    }
}
