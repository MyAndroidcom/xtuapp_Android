package com.xtuapp.zsxd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class SimpleAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> mDatas;
    protected int mItemLayoutId;
    protected int mSize;

    public SimpleAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.context = context;
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public SimpleAdapter(Context context, int size, int itemLayoutId) {
        this.context = context;
        this.mSize = size;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas == null ? mSize : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas == null ? null : mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        viewHolder = ViewHolder.getInstance(context, convertView,
                mItemLayoutId, position);
        View mConverView = viewHolder.getConvertView();
        convert(viewHolder, mConverView, getItem(position), position);
        return mConverView;
    }

    public abstract void convert(ViewHolder viewHolder, View converView,
                                 T item, int position);

    protected void showView(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    protected void dismissView(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }
}
