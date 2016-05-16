package com.xtuapp.zsxd.activity.set;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.base.BaseActivity;
import com.xtuapp.zsxd.adapter.PostAdapter;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class MyMessageActivity extends BaseActivity{
    @ViewInject(R.id.lv_message)
    private ListView mListView;
    @ViewInject(R.id.tl_Toobar)
    private Toolbar mToolbar;

    List<String> mList = new ArrayList<>();
    @Override
    protected int setView() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    setSupportActionBar(mToolbar);
    mToolbar.setTitle("我的发帖");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        for(int i = 0;i < 10;i++){
            mList.add(i + "");
        }
    }

    @Override
    protected void initData(Bundle savedInstanceStatet) {
        mListView.setAdapter(new PostAdapter(this,mList,R.layout.item_topic));
    }
}
