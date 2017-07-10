package cn.lw.yuanbaoapi.view;

/**
 * Created by Administrator on 2017/7/10.
 */

public interface WebView {

    void loadWeb();

    //加载网页出错
    void showError();

    //进度条是否可见
    void showProgress(int visibility);

}
