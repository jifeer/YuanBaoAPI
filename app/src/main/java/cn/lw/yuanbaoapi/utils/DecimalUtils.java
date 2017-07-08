package cn.lw.yuanbaoapi.utils;

import java.math.BigDecimal;

/**
 * Created by lw on 2017/6/29.
 */
public class DecimalUtils {

    /**
     * 保留特定保留数目的小数,这里采取的舍掉保留小数最后一位的所有数字
     * @param scale
     * @param dec
     * @return
     */
    public static String getScale(int scale, double dec){
        return getScale(scale, dec + "");
    }

    /**
     * 保留特定保留数目的小数,这里采取的舍掉保留小数最后一位的所有数字
     * @param scale
     * @param dec
     * @return
     */
    public static String getScale(int scale, String dec){
        BigDecimal bigDecimal = new BigDecimal(dec);
        BigDecimal big = bigDecimal.setScale(scale, BigDecimal.ROUND_DOWN);
        return big + "";
    }
}
