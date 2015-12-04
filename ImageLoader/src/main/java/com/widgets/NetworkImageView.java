package com.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.duanlei.simpleimageloader.core.SimpleImageLoader;

/**
 * Author: duanlei
 * Date: 2015-12-04
 */
public class NetworkImageView extends ImageView {
    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param url
     */
    public void setImageUrl(String url) {
        SimpleImageLoader.getInstance().displayImage(this, url);
    }
}

