package com.duanlei.myphotogallery.view.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.widgets.NetworkImageView;

/**
 * Author: duanlei
 * Date: 2015-08-04
 */
public class ViewHolder {

    private SparseArray<View> mViews;

    private int mPosition;
    private View mConvertView;


    public ViewHolder(Context context, ViewGroup parent,
                      int layoutId, int position) {

        this.mPosition = position;
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context)
                .inflate(layoutId, parent, false);

        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position
                                 ) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public int getmPosition() {
        return mPosition;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过viewId获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
        }

        return (T) view;
    }

    /**
     * 设置TextView的值
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageUrl(int viewId, String url) {
        NetworkImageView iv = getView(viewId);
        iv.setImageUrl(url);
        return this;
    }


    public ViewHolder setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    public ViewHolder setOnClickLister(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnCheckChangeLister(int checkId, CheckBox.OnCheckedChangeListener listener) {
        CheckBox checkBox = getView(checkId);
        checkBox.setOnCheckedChangeListener(listener);
        return this;
    }

    public ViewHolder setChecked(int checkId, boolean checked) {
        CheckBox checkBox = getView(checkId);
        checkBox.setChecked(checked);
        return this;
    }
}
