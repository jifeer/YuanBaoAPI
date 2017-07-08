package cn.lw.yuanbaoapi.di;

import cn.lw.yuanbaoapi.di.dagger2.AppComponent;

/**
 * Created by lw on 2017/7/4.
 */
public class ComponentHolder {
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static void setAppComponent(AppComponent component) {
        appComponent = component;
    }
}
