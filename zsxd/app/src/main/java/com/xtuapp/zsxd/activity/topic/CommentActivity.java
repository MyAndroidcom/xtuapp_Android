package com.xtuapp.zsxd.activity.topic;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.base.BaseActivity;
import com.xtuapp.zsxd.adapter.SimpleAdapter;
import com.xtuapp.zsxd.adapter.ViewHolder;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/8 0008.
 */
public class CommentActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.lv_post)
    private ListView mListView;
    @ViewInject(R.id.iv_back)
    private ImageButton mBackbtn;
    List mList = new ArrayList();
    @Override
    protected int setView() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBackbtn.setOnClickListener(this);
        for(int i = 0;i < 10;i++){
            mList.add(i);
        }
        View view = View.inflate(this,R.layout.item_topic,null);
        mListView.addHeaderView(view);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mListView.setAdapter(new SimpleAdapter<String>(this, mList.size(), R.layout.item_comment) {
            @Override
            public void convert(ViewHolder viewHolder, View converView, String item, int position) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
