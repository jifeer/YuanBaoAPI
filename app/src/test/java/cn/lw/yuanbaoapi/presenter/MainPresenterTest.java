package cn.lw.yuanbaoapi.presenter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import cn.lw.yuanbaoapi.BuildConfig;
import cn.lw.yuanbaoapi.MyRobolectricTestRunner;
import cn.lw.yuanbaoapi.RxAsynRules;
import cn.lw.yuanbaoapi.api.YuanbaoInterface;
import cn.lw.yuanbaoapi.commons.Constant;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.presenter.MainActivity.CoinsTodayPresenter;
import cn.lw.yuanbaoapi.presenter.MainActivity.CoinsTodayPresenterImpl;
import cn.lw.yuanbaoapi.view.CoinsTodayView;
import io.reactivex.Observable;

/**
 * Created by lw on 2017/7/5.
 */
@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainPresenterTest {
    public CoinsTodayPresenter coinsTodayPresenter;
    @Rule
    public RxAsynRules rules = new RxAsynRules();
    @Mock
    public CoinsTodayView coinsTodayView;
    @Mock
    public YuanbaoInterface yuanbaoInterface;
    @Captor
    private ArgumentCaptor<String> mApiObservableCaptor;

    @BeforeClass
    public static void setUpRxSchedulers() {
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        coinsTodayPresenter = new CoinsTodayPresenterImpl(yuanbaoInterface,coinsTodayView);
    }

    /**
     * 测试showProgress和disMissProgress是否成功调用
     */
    @Test
    public void loadSuccess(){
        Mockito.when(yuanbaoInterface.getCoin(Constant.COIN_DODGE)).thenReturn(Observable.just(new Coin()));
        coinsTodayPresenter.loadCoins();
        Mockito.verify(coinsTodayView).showProgress();
        Mockito.verify(coinsTodayView).disMissProgress();
    }
}

