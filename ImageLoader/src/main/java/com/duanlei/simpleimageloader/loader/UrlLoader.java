package com.duanlei.simpleimageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.duanlei.simpleimageloader.request.BitmapRequest;
import com.duanlei.simpleimageloader.utils.BitmapDecoder;
import com.jakewharton.disklrucache.Util;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author: duanlei
 * Date: 2015-12-04
 *
 * 网络图片加载器
 *
 */
public class UrlLoader extends AbsLoader {

    @Override
    public Bitmap onLoadImage(BitmapRequest request) {
        final String imageUrl = request.imageUri;
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(conn.getInputStream());
            is.mark(is.available());

            final InputStream inputStream = is;
            BitmapDecoder bitmapDecoder = new BitmapDecoder() {

                @Override
                public Bitmap decodeBitmapWithOption(BitmapFactory.Options options) {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                    //
                    if (options.inJustDecodeBounds) {
                        try {
                            inputStream.reset();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // 关闭流
                        conn.disconnect();
                    }
                    return bitmap;
                }
            };

            return bitmapDecoder.decodeBitmap(request.getImageViewWidth(),
                    request.getImageViewHeight());
        } catch (Exception e) {

        } finally {
            Util.closeQuietly(is);
            Util.closeQuietly(fos);
        }

        return null;
    }

}
