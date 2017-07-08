package cn.lw.yuanbaoapi.view;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface MainView {

    void switchToTodayInfo();

    void switchToHistoryInfo();

    void setToolbarTitle(String title);

    void setMenuSelected(int id);
}
