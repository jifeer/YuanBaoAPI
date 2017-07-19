package cn.lw.yuanbaoapi.sqlite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import cn.lw.yuanbaoapi.BuildConfig;
import cn.lw.yuanbaoapi.MyRobolectricTestRunner;
import cn.lw.yuanbaoapi.dao.CoinDao;
import cn.lw.yuanbaoapi.entity.Coin;

/**
 * Created by Administrator on 2017/7/19.
 */
@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23, application = RoboApp.class)
public class CoinDaoTest {
    CoinDao coinDao;
    @Before
    public void setUp(){
        coinDao = CoinDao.getInstance(RuntimeEnvironment.application);
    }

    @Test
    public void testInsert(){
        Coin coin = new Coin();
        coin.setName("test1");
        coin.setBuy("testBuy");

        long index = coinDao.insertCoin(coin, false);
        Assert.assertTrue(index > 0);

        Coin bean = coinDao.queryTheLastCoin();
        Assert.assertEquals("test1", bean.getName());
        Assert.assertEquals("testBuy", bean.getBuy());
    }

    @Test
    public void testQuery(){
        Coin coin = new Coin();
        Coin coin1 = new Coin();
        long index1 = coinDao.insertCoin(coin, false);
        long index2 = coinDao.insertCoin(coin1, false);
        Assert.assertTrue(index1 > 0);
        Assert.assertTrue(index2 > 0);

        List<Coin> coins = coinDao.query(0);
        Assert.assertEquals(2, coins.size());
    }

//    @After
//    public void closeDB(){
//        coinDao.closeDb();
//    }
}
