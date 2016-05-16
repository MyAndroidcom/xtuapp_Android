package com.xtuapp.zsxd.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xtuapp.zsxd.activity.topic.CommentActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class PostAdapter extends SimpleAdapter<String> implements View.OnClickListener {

    public PostAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder viewHolder, View converView, String item, int position) {
        converView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, CommentActivity.class);
        context.startActivity(intent);
    }
}
