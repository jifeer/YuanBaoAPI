package cn.lw.yuanbaoapi.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/7/20.
 * 自带EmptyView的RecyclerView
 *
 */

public class EmptyRecyclerView extends RecyclerView {

    public EmptyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private View mEmptyView;

    //AdapterDataObserver监听RecyclerView的数据变化
    private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            Adapter adapter = getAdapter();
            if (adapter.getItemCount() != 0){
                mEmptyView.setVisibility(View.GONE);
                EmptyRecyclerView.this.setVisibility(View.VISIBLE);
            }else{
                mEmptyView.setVisibility(View.VISIBLE);
                EmptyRecyclerView.this.setVisibility(View.GONE);
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
        }
    };

    //设置空数据视图
    public void setEmptyView(View view){
        this.mEmptyView = view;
        ((ViewGroup) getRootView()).addView(view);
    }

    public void setAdapter(Adapter adapter){
        super.setAdapter(adapter);
        adapter.registerAdapterDataObserver(observer);
        observer.onChanged();
    }

}
