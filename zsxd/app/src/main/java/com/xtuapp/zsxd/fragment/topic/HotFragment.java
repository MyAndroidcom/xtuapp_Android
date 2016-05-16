package com.xtuapp.zsxd.fragment.topic;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.adapter.PostAdapter;
import com.xtuapp.zsxd.fragment.base.BaseFragment;

import org.xutils.view.annotation.ViewInject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by Administrator on 2016/4/7 0007.
 */
public class HotFragment extends BaseFragment{
    @ViewInject(R.id.lv_message)
    private ListView mListView;
    List<String> mList = new ArrayList();
    private Intent intent;
    private PullToRefreshLayout ptrl;
    private boolean isFirstIn=true;
    @Override
    public int setView() {
        return R.layout.fragment_post;
    }

    public void initView(Bundle savedInstanceState) {

        for(int i = 0;i < 20;i++){
            mList.add(i + "");
        }
        ptrl = ((PullToRefreshLayout) getView().findViewById(R.id.refresh_view));
        // 此处设置下拉刷新或上拉加载更多监听器
        ptrl.setOnPullListener(new MyPullListener());
        // 设置带gif动画的上拉头与下拉头
        try
        {
            ptrl.setGifRefreshView(new GifDrawable(getResources(), R.drawable.anim));
            ptrl.setGifLoadmoreView(new GifDrawable(getResources(), R.drawable.anim));

        } catch (Resources.NotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mListView = (ListView) ptrl.getPullableView();
        // 第一次进入自动刷新
        if (isFirstIn)
        {
            ptrl.autoRefresh();
            isFirstIn = false;
        }
    }
//    public void onWindowFocusChanged(boolean hasFocus)
//    {
//        // 第一次进入自动刷新
//        if (isFirstIn)
//        {
//            ptrl.autoRefresh();
//            isFirstIn = false;
//        }
//    }
    /**
     * 下拉刷新与上拉加载更多监听器
     */
    public class MyPullListener implements PullToRefreshLayout.OnPullListener {

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 千万别忘了告诉控件刷新完毕了哦！
                    toast("数据加载成功！！！");
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 2000);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载更多操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 千万别忘了告诉控件加载完毕了哦！
                    toast("数据加载成功！！！");
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 2000);
        }
    }


    public void initData(Bundle savedInstanceState) {
        mListView.setAdapter(new PostAdapter(getContext(),mList,R.layout.item_topic));

    }
}
