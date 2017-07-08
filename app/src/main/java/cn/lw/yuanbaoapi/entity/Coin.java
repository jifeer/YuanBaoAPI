package cn.lw.yuanbaoapi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lw on 2017/6/29.
 */
public class Coin {

    /**
     * name : YBC
     * logo : https://www.yuanbao.com/images/coin/coin_ybc.png
     * price : 54.51
     * max : 57.00
     * min : 51.08
     * buy : 54.51
     * sale : 55.00
     * available_supply : 3006599
     * market_cap : 1.6388971149E8
     * volume_24h : 169362.6597
     * change_24h : 0.0605
     * Website : http://www.ybcoin.com/
     * Markets : https://www.yuanbao.com/trade/ybc2cny/index
     */

    @SerializedName("name")
    private String name;
    @SerializedName("logo")
    private String logo;
    @SerializedName("price")
    private String price;
    @SerializedName("max")
    private String max;
    @SerializedName("min")
    private String min;
    @SerializedName("buy")
    private String buy;
    @SerializedName("sale")
    private String sale;
    @SerializedName("available_supply")
    private long available_supply;
    @SerializedName("market_cap")
    private double market_cap;
    @SerializedName("volume_24h")
    private String volume_24h;
    @SerializedName("change_24h")
    private double change_24h;
    @SerializedName("Website")
    private String Website;
    @SerializedName("Markets")
    private String Markets;

    public Coin() {
    }

    public Coin(String name, String logo, String price, String max, String min, String buy, String sale, int available_supply, double market_cap, String volume_24h, double change_24h, String website, String markets) {
        this.name = name;
        this.logo = logo;
        this.price = price;
        this.max = max;
        this.min = min;
        this.buy = buy;
        this.sale = sale;
        this.available_supply = available_supply;
        this.market_cap = market_cap;
        this.volume_24h = volume_24h;
        this.change_24h = change_24h;
        Website = website;
        Markets = markets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public long getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(int available_supply) {
        this.available_supply = available_supply;
    }

    public double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(double market_cap) {
        this.market_cap = market_cap;
    }

    public String getVolume_24h() {
        return volume_24h;
    }

    public void setVolume_24h(String volume_24h) {
        this.volume_24h = volume_24h;
    }

    public double getChange_24h() {
        return change_24h;
    }

    public void setChange_24h(double change_24h) {
        this.change_24h = change_24h;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String Website) {
        this.Website = Website;
    }

    public String getMarkets() {
        return Markets;
    }

    public void setMarkets(String Markets) {
        this.Markets = Markets;
    }

    @Override
    public String toString() {
        return getName();
    }
}
