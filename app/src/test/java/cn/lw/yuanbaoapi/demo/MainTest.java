package cn.lw.yuanbaoapi.demo;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import cn.lw.yuanbaoapi.BuildConfig;
import cn.lw.yuanbaoapi.MyRobolectricTestRunner;
import cn.lw.yuanbaoapi.R;
import cn.lw.yuanbaoapi.demo.utils.PasswordValidator;
import cn.lw.yuanbaoapi.demo.utils.UserManager;
import cn.lw.yuanbaoapi.di.ComponentHolder;
import cn.lw.yuanbaoapi.di.dagger2.AppComponent;
import cn.lw.yuanbaoapi.di.dagger2.AppModule;
import cn.lw.yuanbaoapi.di.dagger2.DaggerAppComponent;

/**
 * Created by lw on 2017/7/1.
 */
@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class MainTest {

    //Run注解的对象必须是public
    @Rule
    public MethodNameExample example = new MethodNameExample();
    //检测运行时间是否在特定时间以内
    //public Timeout time = new Timeout(6, TimeUnit.SECONDS);
    private Calculator calculator;

    //表示在所有的测试方法执行前执行
    @Before
    public void init(){
        calculator = new Calculator();
    }

    @Test
    public void testMainActivity(){
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.findViewById(R.id.btn).performClick();
    }

    @Test
    public void testLogin() throws Exception{
        String testLoginName = "测试";
        String testLoginPword = "1234567";
        //Mockito定义方法返回值
        PasswordValidator validator = Mockito.mock(PasswordValidator.class);
        //when参数指定的时候才返回thenReturn制定的值
        Mockito.when(validator.isValid("测试")).thenReturn(true);
        UserManager manager = Mockito.mock(UserManager.class);
        MockiToLoginPresenter presenter = new MockiToLoginPresenter(manager, validator);
        presenter.login(testLoginName, testLoginPword);
        //Mokito定义检测代码是否执行
        Mockito.verify(manager).performLogin(testLoginName, testLoginPword);
    }

    //检测是否抛出既定的异常
    @Test(expected = IllegalArgumentException.class)
    public void testCalculator(){
        calculator.divide(3, 0);
    }

    //带dagger的测试
    @Test
    public void testDagger(){
        AppModule appModule = Mockito.spy(new AppModule(RuntimeEnvironment.application));
        MockiToLoginPresenter presenter = Mockito.mock(MockiToLoginPresenter.class);
        Mockito.when(appModule.provideLoginPresenter(Mockito.any(UserManager.class),Mockito.any(PasswordValidator.class))).thenReturn(presenter);
        //LoginActivity中需要调用inject方法所以此处需要对AppComponent初始化
        AppComponent appComponent = DaggerAppComponent.builder().appModule(appModule).build();
        ComponentHolder.setAppComponent(appComponent);

        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        ((EditText) loginActivity.findViewById(R.id.et_password)).setText("username");
        ((EditText) loginActivity.findViewById(R.id.et_username)).setText("password");
        loginActivity.findViewById(R.id.btn_login).performClick();

        Mockito.verify(presenter).login("username", "password");
    }



}
