package cn.lw.yuanbaoapi.di.dagger2;

import javax.inject.Singleton;

import cn.lw.yuanbaoapi.demo.LoginActivity;
import cn.lw.yuanbaoapi.demo.MockiToLoginPresenter;
import dagger.Component;

/**
 * Created by Administrator on 2017/7/4.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

     MockiToLoginPresenter getLoginPresenter();

     //初始化的Context位置必须指明,否则MockiToLoginPresenter找不到初始化位置,报NullPointerException错误
     void inject(LoginActivity context);
}
