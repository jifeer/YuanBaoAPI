package cn.lw.yuanbaoapi.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cn.lw.yuanbaoapi.presenter.WebViewActivity.WebViewPersenter;
import cn.lw.yuanbaoapi.presenter.WebViewActivity.WebViewPresenterImpl;
import cn.lw.yuanbaoapi.view.WebView;

/**
 * Created by Administrator on 2017/7/10.
 */
public class WebViewPresenterTest {
    @Mock
    public WebView webView;
    public WebViewPersenter webViewPersenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        webViewPersenter = new WebViewPresenterImpl(webView);
    }

    @Test
    public void testLoadSuccess(){

    }

    @Test
    public void testLoadFail(){

    }
}
