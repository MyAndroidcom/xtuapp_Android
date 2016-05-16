package com.xtuapp.zsxd.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

public class ViewHolder {
	private final SparseArray<View> mViewArray;
	private View mConvertView;

	private ViewHolder(Context context, int layoutId, int position) {
		mViewArray = new SparseArray<View>();
		mConvertView = View.inflate(context, layoutId, null);
		mConvertView.setTag(this);
	}

	public static ViewHolder getInstance(Context context, View convertView, int layoutId, int position) {

		if (convertView == null) {
			return new ViewHolder(context, layoutId, position);
		}
		return (ViewHolder) convertView.getTag();
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {

		View view = mViewArray.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViewArray.put(viewId, view);
		}
		return (T) view;
	}

	public View getConvertView() {
		return mConvertView;
	}
}
