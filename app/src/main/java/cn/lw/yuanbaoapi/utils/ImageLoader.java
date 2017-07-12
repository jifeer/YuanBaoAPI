package cn.lw.yuanbaoapi.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import cn.lw.yuanbaoapi.R;

/**
 * Created by lw on 2017/6/29.
 */
public class ImageLoader {

    public static void loadImage(ImageView view, String imgUrl){
        Picasso.with(view.getContext()).load(imgUrl).error(R.drawable.ic_error).into(view);
    }

    public static void loadImageWithCenterCropMode(ImageView view, String imgUrl){
        Picasso.with(view.getContext()).load(imgUrl).centerCrop().error(R.drawable.ic_error).into(view);
    }

    public static void loadLocalImage(ImageView view, int resId){
        Picasso.with(view.getContext()).load(resId).error(R.drawable.ic_error).into(view);
    }
}
