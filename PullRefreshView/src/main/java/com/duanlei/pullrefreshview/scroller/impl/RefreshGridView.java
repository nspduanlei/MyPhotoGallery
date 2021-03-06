package com.duanlei.pullrefreshview.scroller.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.duanlei.pullrefreshview.scroller.RefreshAdaterView;


public class RefreshGridView extends RefreshAdaterView<GridView> {

    public RefreshGridView(Context context) {
        this(context, null);
    }

    /**
     * @param context
     * @param attrs
     */
    public RefreshGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void setupContentView(Context context) {
        mContentView = new GridView(context);
        mContentView.setNumColumns(4);
        mContentView.setHorizontalSpacing(8);
        mContentView.setVerticalSpacing(8);
        // 设置滚动监听器
        mContentView.setOnScrollListener(this);
    }

    @Override
    protected boolean isTop() {
        return mContentView.getFirstVisiblePosition() == 0
                && getScrollY() <= mHeaderView.getMeasuredHeight();
    }

    @Override
    protected boolean isBottom() {
        return mContentView != null && mContentView.getAdapter() != null
                && mContentView.getLastVisiblePosition() ==
                mContentView.getAdapter().getCount() - 1 && mYOffset < 0;
    }

}
