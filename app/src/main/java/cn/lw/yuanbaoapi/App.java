package cn.lw.yuanbaoapi;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import cn.lw.yuanbaoapi.di.ComponentHolder;
import cn.lw.yuanbaoapi.di.dagger2.AppComponent;
import cn.lw.yuanbaoapi.di.dagger2.AppModule;
import cn.lw.yuanbaoapi.di.dagger2.DaggerAppComponent;

/**
 * Created by lw on 2017/6/28.
 */
public class App extends Application {
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLeanCanary();
        initDI();
    }

    //dagger初始化
    private void initDI(){
        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        ComponentHolder.setAppComponent(appComponent);
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
