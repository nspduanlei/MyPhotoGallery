package com.duanlei.simpleimageloader.policy;

import com.duanlei.simpleimageloader.request.BitmapRequest;

/**
 * Author: duanlei
 * Date: 2015-12-04
 *
 * 逆序加载策略，即从最后加入队列的请求进行加载
 *
 */
public class ReversePolicy implements LoadPolicy {

    @Override
    public int compare(BitmapRequest request1, BitmapRequest request2) {
        // 注意Bitmap请求要先执行最晚加入队列的请求,ImageLoader的策略
        return request2.serialNum - request1.serialNum;
    }

}
