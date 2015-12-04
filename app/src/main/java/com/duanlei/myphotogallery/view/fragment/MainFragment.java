package com.duanlei.myphotogallery.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duanlei.myphotogallery.R;
import com.duanlei.myphotogallery.config.Constants;
import com.duanlei.myphotogallery.config.UrlConstant;
import com.duanlei.myphotogallery.model.GalleryItem;
import com.duanlei.myphotogallery.view.adapter.CommonAdapter;
import com.duanlei.myphotogallery.view.adapter.ViewHolder;
import com.duanlei.pullrefreshview.listener.OnLoadListener;
import com.duanlei.pullrefreshview.listener.OnRefreshListener;
import com.duanlei.pullrefreshview.scroller.impl.RefreshGridView;
import com.duanlei.simplenet.base.Request;
import com.duanlei.simplenet.core.RequestQueue;
import com.duanlei.simplenet.core.SimpleNet;
import com.duanlei.simplenet.requests.JsonRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Author: duanlei
 * Date: 2015-12-04
 */
public class MainFragment extends Fragment{

    private static final String TAG = "MainFragment";

    private RefreshGridView mGridView;

    private CommonAdapter mAdapter;

    private ArrayList<GalleryItem> mItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mGridView = (RefreshGridView) v.findViewById(R.id.gridView);

        mGridView.setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoadMore() {
                Log.d(TAG, "onLoadMore");
                mGridView.refreshComplete();
            }
        });

        mGridView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh");

                mGridView.loadCompelte();
            }
        });

        initData();

        obtainHttpData();
        return v;
    }

    /**
     * 初始化数据
     */
    public void initData() {
        mItems = new ArrayList<>();
        initAdapter();
        mGridView.setAdapter(mAdapter);
    }

    /**
     * 初始化适配器
     */
    public void initAdapter() {
        mAdapter = new CommonAdapter<GalleryItem>(getActivity(), mItems, R.layout.item_gallery) {
            @Override
            public void convert(ViewHolder holder, GalleryItem galleryItem) {
                holder.setImageResource(R.id.imageView, R.mipmap.ic_launcher);
            }
        };
    }


    RequestQueue queue;

    //请求网络数据
    public void obtainHttpData() {
        //1、构建请求队列
        queue = SimpleNet.newRequestQueue();
        //2、创建请求
        JsonRequest jsonRequest = new JsonRequest(
                Request.HttpMethod.GET,
                UrlConstant.ENDPOINT_NEW,
                new Request.RequestListener<JSONObject>() {

            @Override
            public void onComplete(int stCode, JSONObject response, String errMsg) {
                Log.d(TAG, "数据请求成功");
            }
        });

        //3、添加参数
        Map<String, String> params = jsonRequest.getParams();
        params.put("key", Constants.API_KEY);
        params.put("returntype", "json");
        params.put("p", "1");
        params.put("cid", "15");

        queue.addRequest(jsonRequest);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (queue != null)
            queue.stop();
    }
}