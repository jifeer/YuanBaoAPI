package cn.lw.yuanbaoapi.presenter.WebViewActivity;

import cn.lw.yuanbaoapi.view.WebView;

/**
 * Created by Administrator on 2017/7/10.
 */

public class WebViewPresenterImpl implements WebViewPersenter {
    private WebView webView;
    public WebViewPresenterImpl(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void loadWeb() {
        this.webView.loadWeb();
    }

    @Override
    public void showError() {
        webView.showError();
    }

    @Override
    public void showProgress(int visibility) {
        this.webView.showProgress(visibility);
    }
}
