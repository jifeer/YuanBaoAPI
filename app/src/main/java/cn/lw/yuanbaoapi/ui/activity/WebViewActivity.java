package cn.lw.yuanbaoapi.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lw.yuanbaoapi.R;
import cn.lw.yuanbaoapi.commons.net.NetType;
import cn.lw.yuanbaoapi.presenter.WebViewActivity.WebViewPersenter;
import cn.lw.yuanbaoapi.presenter.WebViewActivity.WebViewPresenterImpl;
import cn.lw.yuanbaoapi.utils.ImageLoader;

public class WebViewActivity extends BaseActivity implements cn.lw.yuanbaoapi.view.WebView{

    private static final String YUANBAO_COINS_URL = "https://www.yuanbao.com/coins";
    private static final String WEBVIEW_LOGO = "https://ybh-static.oss-cn-hangzhou.aliyuncs.com/images/ybc_logo_201604281200.png";

    @BindView(R.id.backdrop) ImageView backdrop;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.webView) WebView webView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.activity_web_view) CoordinatorLayout activityWebView;
    @BindView(R.id.appbar) AppBarLayout appbar;
    @BindView(R.id.img_web_error) ImageButton imgError;

    private float downP;
    private WebViewPersenter webViewPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        webViewPersenter = new WebViewPresenterImpl(this);
        webViewPersenter.loadWeb();
        webViewPersenter.showProgress(View.VISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        //防止打开手机自带浏览器
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webViewPersenter.showProgress(View.GONE);
            }
        });
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getPointerCount() > 1){
                    return false;
                }
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        downP = event.getX();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                        event.setLocation(downP, event.getY());
                        break;
                }
                return false;
            }
        });
        webView.setHorizontalScrollBarEnabled(true);
        initCollaspingToolbar();
    }

    private void initCollaspingToolbar() {
        collapsingToolbar.setTitle(" ");
        appbar.setExpanded(true);

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("元宝网");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void loadWeb() {
        progressBar.setVisibility(View.GONE);
        imgError.setVisibility(View.VISIBLE);
        webView.setVisibility(View.VISIBLE);
        webView.loadUrl(YUANBAO_COINS_URL);
        ImageLoader.loadImage(backdrop, WEBVIEW_LOGO);
    }

    @Override
    public void showError() {
        progressBar.setVisibility(View.GONE);
        imgError.setVisibility(View.VISIBLE);
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onConnect(NetType netType) {
        super.onConnect(netType);
        loadWeb();
    }

    @Override
    public void disConnect() {
        super.disConnect();
        showError();
    }

    @Override
    public void showProgress(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
