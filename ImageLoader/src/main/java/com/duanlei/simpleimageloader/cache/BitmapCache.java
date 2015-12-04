package com.duanlei.simpleimageloader.cache;

import android.graphics.Bitmap;

import com.duanlei.simpleimageloader.request.BitmapRequest;

/**
 * Author: duanlei
 * Date: 2015-12-04
 * 图片缓存抽象类
 */
public interface BitmapCache {

    public Bitmap get(BitmapRequest key);
    public void put(BitmapRequest key, Bitmap value);
    public void remove(BitmapRequest key);
}
