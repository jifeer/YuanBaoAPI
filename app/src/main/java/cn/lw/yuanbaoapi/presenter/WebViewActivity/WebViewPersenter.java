package cn.lw.yuanbaoapi.presenter.WebViewActivity;

/**
 * Created by Administrator on 2017/7/10.
 */

public interface WebViewPersenter {

    void loadWeb();

    void showError();

    void showProgress(int visibility);
}
