package cn.lw.yuanbaoapi.presenter;

import android.app.Activity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowWebView;

import cn.lw.yuanbaoapi.BuildConfig;
import cn.lw.yuanbaoapi.MyRobolectricTestRunner;
import cn.lw.yuanbaoapi.R;
import cn.lw.yuanbaoapi.common.ShadowCollapsingToolbarLayout;
import cn.lw.yuanbaoapi.commons.Urls;
import cn.lw.yuanbaoapi.presenter.WebViewActivity.WebViewPersenter;
import cn.lw.yuanbaoapi.presenter.WebViewActivity.WebViewPresenterImpl;
import cn.lw.yuanbaoapi.ui.activity.WebViewActivity;

/**
 * Created by Administrator on 2017/7/10.
 */
@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowCollapsingToolbarLayout.class})
public class WebViewPresenterTest {
    public WebView webView;
    public Activity activity;
    public ImageButton btnError;
    private ShadowWebView shadowWebView;

    public WebViewPersenter presenter;
    @Mock
    public cn.lw.yuanbaoapi.view.WebView baseView;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.setupActivity(WebViewActivity.class);
        webView = (WebView) activity.findViewById(R.id.webView);
        btnError = (ImageButton) activity.findViewById(R.id.img_web_error);
        shadowWebView = Shadows.shadowOf(webView);
    }

    @Test
    public void testNotNull(){
        Assert.assertNotNull(activity);
        Assert.assertEquals(activity.getTitle(), "元宝API");
        Assert.assertNotNull(webView);
    }

    @Test
    public void testUrlEqual(){
        Assert.assertEquals(Urls.YUANBAO_COINS_URL, shadowWebView.getLastLoadedUrl());
    }

    @Test
    public void testWebViewSetting(){
        WebSettings webSettings = webView.getSettings();
        Assert.assertNotNull(webSettings);
        //测试是否允许JS运行
        Assert.assertTrue(webSettings.getJavaScriptEnabled());
        Assert.assertTrue(webSettings.getBuiltInZoomControls());
    }

    @Test
    public void testWebviewClient(){
        Assert.assertNotNull(shadowWebView.getWebViewClient());
    }

    //检验View的reloadWeb是否得到调用
    @Test
    public void testError(){
        presenter = new WebViewPresenterImpl(baseView);
        presenter.reloadWeb();
        Mockito.verify(baseView).reloadWeb();
    }

}
