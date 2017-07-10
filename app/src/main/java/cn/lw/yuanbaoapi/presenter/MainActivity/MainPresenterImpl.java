package cn.lw.yuanbaoapi.presenter.MainActivity;

import cn.lw.yuanbaoapi.view.MainView;

/**
 * Created by lw on 2017/6/28.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    public MainPresenterImpl(MainView view) {
        mainView = view;
    }

    @Override
    public void switchView(int position) {
        switch (position){
            case 0:
                mainView.switchToTodayInfo();
                mainView.setToolbarTitle("今日行情");
                mainView.setMenuSelected(position);
                break;
            case 1:
                mainView.switchToHistoryInfo();
                mainView.setToolbarTitle("历史行情");
                mainView.setMenuSelected(position);
                break;
        }
    }
}
