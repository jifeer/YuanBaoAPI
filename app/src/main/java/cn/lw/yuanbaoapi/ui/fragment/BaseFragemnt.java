package cn.lw.yuanbaoapi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lw.yuanbaoapi.R;

/**
 * Created by lw on 2017/6/28.
 */
public abstract class BaseFragemnt extends Fragment {
    @BindView(R.id.img_empty)
    ImageView imgEmpty;
    @BindView(R.id.content)
    FrameLayout content;
    Unbinder unbinder;
    private String title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_base_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        View contentView = initContentView();
        //子View非空判断
        if (null != contentView) {
            content.addView(contentView);
        }
        return view;
    }

    public abstract View initContentView();


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
