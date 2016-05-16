package com.xtuapp.zsxd.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.fragment.HomeFragment;
import com.xtuapp.zsxd.fragment.MyFragment;
import com.xtuapp.zsxd.fragment.NewsFragment;
import com.xtuapp.zsxd.fragment.TopicFragment;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public class HomeActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{

    @ViewInject(R.id.vp_home)
    private ViewPager viewPager;

    List<Fragment> listFragment;
    @ViewInject(R.id.ra_group)
    private RadioGroup radiogroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        x.view().inject(this);
        initView();
        initData();
    }


    private void initView() {
        listFragment = new ArrayList<>();
        listFragment.add(new HomeFragment());
        listFragment.add(new NewsFragment());
        listFragment.add(new TopicFragment());
        listFragment.add(new MyFragment());
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        radiogroup.setOnCheckedChangeListener(this);
    }

    private void initData() {
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_home:
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.rb_news:
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.rb_topic:
                viewPager.setCurrentItem(2,false);
                break;
            case R.id.rb_my:
                viewPager.setCurrentItem(3,false);
                break;
        }

    }

    private class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }

    }
}
