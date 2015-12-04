package com.duanlei.simpleimageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.duanlei.simpleimageloader.request.BitmapRequest;

/**
 * Author: duanlei
 * Date: 2015-12-04
 *
 * 图片内存缓存，key为图片的uri， 值为图片本身
 *
 */
public class MemoryCache implements BitmapCache {
    private LruCache<String, Bitmap> mMemeryCache;

    public MemoryCache() {

        // 计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // 取4分之一的可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        mMemeryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };

    }

    @Override
    public Bitmap get(BitmapRequest key) {
        return mMemeryCache.get(key.imageUri);
    }

    @Override
    public void put(BitmapRequest key, Bitmap value) {
        mMemeryCache.put(key.imageUri, value);
    }

    @Override
    public void remove(BitmapRequest key) {
        mMemeryCache.remove(key.imageUri);
    }
}
