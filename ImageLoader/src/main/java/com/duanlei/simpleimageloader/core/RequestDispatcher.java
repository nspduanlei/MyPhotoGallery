package com.duanlei.simpleimageloader.core;

import android.util.Log;

import com.duanlei.simpleimageloader.loader.Loader;
import com.duanlei.simpleimageloader.loader.LoaderManager;
import com.duanlei.simpleimageloader.request.BitmapRequest;

import java.util.concurrent.BlockingQueue;

/**
 * Author: duanlei
 * Date: 2015-12-04
 *
 * 网络请求Dispatcher,继承自Thread，从网络请求队列中循环读取请求并执行
 *
 */
final class RequestDispatcher extends Thread {

    /**
     * 网络请求队列
     */
    private BlockingQueue<BitmapRequest> mRequestQueue;

    /**
     * @param queue
     */
    public RequestDispatcher(BlockingQueue<BitmapRequest> queue) {
        mRequestQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {
                final BitmapRequest request = mRequestQueue.take();
                if (request.isCancel) {
                    continue;
                }

                final String schema = parseSchema(request.imageUri);
                Loader imageLoader = LoaderManager.getInstance().getLoader(schema);
                imageLoader.loadImage(request);
            }
        } catch (InterruptedException e) {
            Log.i("", "### 请求分发器退出");
        }
    }

    private String parseSchema(String uri) {
        if (uri.contains("://")) {
            return uri.split("://")[0];
        } else {
            Log.e(getName(), "### wrong scheme, image uri is : " + uri);
        }

        return "";
    }

}
