package cn.lw.yuanbaoapi.di.dagger2;

import android.content.Context;

import cn.lw.yuanbaoapi.demo.MockiToLoginPresenter;
import cn.lw.yuanbaoapi.demo.utils.PasswordValidator;
import cn.lw.yuanbaoapi.demo.utils.UserManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lw on 2017/7/4.
 */
@Module
public class AppModule {

    private Context context;
    public AppModule(Context context) {
        this.context = context;
    }

    @Provides public PasswordValidator getPasswordValidator(){
        return new PasswordValidator();
    }

    @Provides public UserManager getUserManager(){
        return new UserManager();
    }

    @Provides public MockiToLoginPresenter provideLoginPresenter(UserManager userManager, PasswordValidator validator) {
        return new MockiToLoginPresenter(userManager, validator);
    }

    @Provides public Context provideContext(){
        return context;
    }
}
