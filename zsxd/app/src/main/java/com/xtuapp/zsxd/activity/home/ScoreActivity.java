package com.xtuapp.zsxd.activity.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.base.BaseActivity;
import com.xtuapp.zsxd.adapter.SimpleAdapter;
import com.xtuapp.zsxd.adapter.ViewHolder;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7 0007.
 */
public class ScoreActivity extends BaseActivity{
    @ViewInject(R.id.lv_score)
    private ListView mListView;
    List<String> mList = new ArrayList<>();
    @ViewInject(R.id.tl_Toobar)
    private Toolbar mToolBar;

    @Override
    protected int setView() {
        return R.layout.activity_score;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //加入ToolBar声明
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        for(int i = 0;i < 10;i++){
            mList.add(i + "");
        }
        mListView.setAdapter(new SimpleAdapter<String>(this,mList,R.layout.item_score) {
            @Override
            public void convert(ViewHolder viewHolder, View converView, String item, int position) {

            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceStatet) {

    }
}
