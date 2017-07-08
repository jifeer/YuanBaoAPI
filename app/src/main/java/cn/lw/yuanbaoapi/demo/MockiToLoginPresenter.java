package cn.lw.yuanbaoapi.demo;

import cn.lw.yuanbaoapi.demo.utils.PasswordValidator;
import cn.lw.yuanbaoapi.demo.utils.UserManager;

/**
 * Created by lw on 2017/7/2.
 */
public class MockiToLoginPresenter {
    private UserManager manager;
    private PasswordValidator validator;

    public MockiToLoginPresenter(UserManager manager, PasswordValidator validator) {
        this.manager = manager;
        this.validator = validator;
    }

    public void login(String userName, String password){
        if (userName == null || userName.length() == 0) return;
        if (password == null || password.length() < 6) return;
        if (validator.isValid(userName)) {
            manager.performLogin(userName, password);
        }
    }

    public void setUserManger(UserManager manger){
        this.manager = manger;
    }

    public void setValidator(PasswordValidator validator){
        this.validator = validator;
    }

}
