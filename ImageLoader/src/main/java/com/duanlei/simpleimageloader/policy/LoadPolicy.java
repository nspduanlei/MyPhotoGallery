package com.duanlei.simpleimageloader.policy;

import com.duanlei.simpleimageloader.request.BitmapRequest;

/**
 * Author: duanlei
 * Date: 2015-12-04
 *
 * 加载策略接口
 */
public interface LoadPolicy {
    public int compare(BitmapRequest request1, BitmapRequest request2);
}
