package cn.lw.yuanbaoapi.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lw.yuanbaoapi.R;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.utils.DecimalUtils;
import cn.lw.yuanbaoapi.utils.ImageLoader;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lw on 2017/6/29.
 */
public class PriOfCoinsAdapter extends RecyclerView.Adapter<PriOfCoinsAdapter.CoinVH> {

    private List<Coin> list;

    public PriOfCoinsAdapter(List<Coin> list) {
        this.list = list;
    }

    @Override
    public CoinVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_coins_list, parent ,false);
        return new CoinVH(view);
    }

    @Override
    public void onBindViewHolder(CoinVH holder, int position) {
        Coin coin = list.get(position);
        ImageLoader.loadImage(holder.imgCoin, list.get(position).getLogo());
        holder.tvBuyIn.setText("买入价:" + coin.getBuy());
        holder.tvCoinName.setText(coin.getName());
        holder.tvCurrentPri.setText("Pri:" + coin.getPrice());
        //保留小数点后两位数字
        holder.tvIn24h.setText("24H:" + DecimalUtils.getScale(2, coin.getChange_24h() * 100) + "%");
        holder.tvUpdateTime.setText("2016/3/3");
        holder.tvSoldOut.setText("卖出价:" + coin.getSale());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void initData(List<Coin> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public static class CoinVH extends RecyclerView.ViewHolder {

        @BindView(R.id.img_coin)
        CircleImageView imgCoin;
        @BindView(R.id.tv_coin_name)
        TextView tvCoinName;
        @BindView(R.id.tv_update_time)
        TextView tvUpdateTime;
        @BindView(R.id.tv_buy_in)
        TextView tvBuyIn;
        @BindView(R.id.tv_sold_out)
        TextView tvSoldOut;
        @BindView(R.id.tv_current_pri)
        TextView tvCurrentPri;
        @BindView(R.id.tv_in_24h)
        TextView tvIn24h;

        public CoinVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
