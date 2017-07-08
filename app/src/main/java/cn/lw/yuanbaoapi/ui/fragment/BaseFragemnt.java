package cn.lw.yuanbaoapi.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by lw on 2017/6/28.
 */
public class BaseFragemnt extends Fragment {
    private String title;
    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
