package com.duanlei.simpleimageloader.loader;

import android.graphics.Bitmap;
import android.util.Log;

import com.duanlei.simpleimageloader.request.BitmapRequest;

/**
 * Author: duanlei
 * Date: 2015-12-04
 */
public class NullLoader extends AbsLoader {
    @Override
    protected Bitmap onLoadImage(BitmapRequest result) {
        Log.e(NullLoader.class.getSimpleName(), "### wrong schema, your image uri is : "
                + result.imageUri);
        return null;
    }
}
