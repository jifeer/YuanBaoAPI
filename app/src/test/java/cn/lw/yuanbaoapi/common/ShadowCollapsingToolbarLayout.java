package cn.lw.yuanbaoapi.common;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowFrameLayout;

/**
 * Created by Administrator on 2017/7/11.
 * 因为Robolitrics不支持CollapsingToolbarLayout,所以我们需要自定义一个ShadowCollapsingToolbarLayout来支持Robolitrics使用
 * 对于自定义的类需要在使用测试的时候通过@Config(shadows = ShadowCollapsingToolbarLayout.class)注入进去
 */
@Implements(CollapsingToolbarLayout.class)
public class ShadowCollapsingToolbarLayout extends ShadowFrameLayout{

    public void __constructor__(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Implementation public void onAttachedToWindow() {}

    @Implementation public void setTitle(){}

}
