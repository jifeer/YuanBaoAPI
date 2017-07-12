package cn.lw.yuanbaoapi.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
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

import static cn.lw.yuanbaoapi.commons.Urls.RANDOM_IMG;
import static cn.lw.yuanbaoapi.commons.Urls.YUANBAO_COINS_URL;

public class WebViewActivity extends BaseActivity implements cn.lw.yuanbaoapi.view.WebView{


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

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                loadError();
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
        imgError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(View.VISIBLE);
                reloadWeb();
            }
        });
    }

    private void initCollaspingToolbar() {
        toolbar.setTitle(" ");
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
                    toolbar.setTitle("元宝网");
                    isShow = true;
                } else if (isShow) {
                    toolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void loadSuccess() {
        showProgress(View.GONE);
        imgError.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        webView.loadUrl(YUANBAO_COINS_URL);
        ImageLoader.loadImage(backdrop, RANDOM_IMG);
    }

    @Override
    public void loadError() {
        showProgress(View.GONE);
        imgError.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);
    }

    @Override
    public void onConnect(NetType netType) {
        super.onConnect(netType);
        loadSuccess();
    }

    @Override
    public void disConnect() {
        super.disConnect();
        loadError();
    }

    @Override
    public void showProgress(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void reloadWeb() {
        webView.reload();
    }
}
