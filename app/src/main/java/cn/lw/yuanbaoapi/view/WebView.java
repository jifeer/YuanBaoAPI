package cn.lw.yuanbaoapi.view;

/**
 * Created by Administrator on 2017/7/10.
 */

public interface WebView {

    void loadSuccess();

    //加载网页出错
    void loadError();

    //进度条是否可见
    void showProgress(int visibility);

    //重新加载网页
    void reloadWeb();

}
