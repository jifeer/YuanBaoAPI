package cn.lw.yuanbaoapi.demo;

/**
 * Created by lw on 2017/7/2.
 */
public class Calculator {

    public int add(int one, int another) {
        // 为了简单起见，暂不考虑溢出等情况。
        return one + another;
    }

    public int multiply(int one, int another) {
        // 为了简单起见，暂不考虑溢出等情况。
        return one * another;
    }

    public int divide(int amount , int divide){
        if (divide == 0) throw new IllegalArgumentException("cant be 0");
        return amount / divide;
    }
}
