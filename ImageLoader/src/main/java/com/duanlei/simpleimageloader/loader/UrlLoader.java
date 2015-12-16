package com.duanlei.simpleimageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.duanlei.simpleimageloader.request.BitmapRequest;
import com.duanlei.simpleimageloader.utils.BitmapDecoder;

import java.io.ByteArrayOutputStream;
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
        //FileOutputStream fos = null;
        //InputStream is = null;
        try {
            URL url = new URL(imageUrl);
//            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            final byte[] bitmapBytes = getUrlBytes(imageUrl);

//            is = new BufferedInputStream(conn.getInputStream());
//            is.mark(is.available());

            //final InputStream inputStream = is;
            BitmapDecoder bitmapDecoder = new BitmapDecoder() {
                @Override
                public Bitmap decodeBitmapWithOption(BitmapFactory.Options options) {

                    //Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);

                    //                    if (options.inJustDecodeBounds) {
//                        try {
//                            inputStream.mark(inputStream.available());
//                            inputStream.reset();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        // 关闭流
//                        conn.disconnect();
//                    }

                    return BitmapFactory
                            .decodeByteArray(bitmapBytes, 0, bitmapBytes.length, options);
                }
            };

            return bitmapDecoder.decodeBitmap(request.getImageViewWidth(),
                    request.getImageViewHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            Util.closeQuietly(is);
//            Util.closeQuietly(fos);
//        }

        return null;
    }


    byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];

            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }

            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

}
