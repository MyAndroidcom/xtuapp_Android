package com.xtuapp.zsxd.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.topic.PostCommentActivity;
import com.xtuapp.zsxd.fragment.base.BaseFragment;
import com.xtuapp.zsxd.fragment.topic.HotFragment;
import com.xtuapp.zsxd.fragment.topic.MessageFragment;
import com.xtuapp.zsxd.fragment.topic.QuintessenceFragment;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public class TopicFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    @ViewInject(R.id.vp_menu_detail)
    private ViewPager mViewpager;
    private float width ;
    List<Fragment> listFragment;
    @ViewInject(R.id.ra_group)
    private RadioGroup radioGroup;

    @ViewInject(R.id.vi_indicator)
    private View mView;
    @ViewInject(R.id.fa_button)
    private FloatingActionButton mFloatBtn;
    @Override
    public int setView() {
        return R.layout.fragment_topic;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        listFragment = new ArrayList<>();
        listFragment.add(new MessageFragment());
        listFragment.add(new HotFragment());
        listFragment.add(new QuintessenceFragment());
        mViewpager.setAdapter(new FragemntAdapter(getChildFragmentManager()));
        radioGroup.setOnCheckedChangeListener(this);
        mViewpager.addOnPageChangeListener(this);
        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PostCommentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
       width =  getWindowWidth() / listFragment.size();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_mess:
                mView.setX(0);
                mViewpager.setCurrentItem(0);
                break;
            case R.id.rb_hot:
                mView.setX(width);
                mViewpager.setCurrentItem(1);
                break;
            case R.id.rb_quint:
                mView.setX(width * 2);
                mViewpager.setCurrentItem(2);
                break;
           /* case R.id.rb_live:
                mView.setX(width * 3);
                mViewpager.setCurrentItem(3);
                break;*/
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//        ((RadioButton)radioGroup.getChildAt(position)).setTextColor();
        if (position + 1 < radioGroup.getChildCount()) {
            ((RadioButton) radioGroup.getChildAt(position + 1)).setTextColor(Color.rgb((int) ((03 - 114) * positionOffset + 114), (int) ((169 - 114) * positionOffset + 114), (int) ((244 - 114) * positionOffset + 114)));
        }
        ((RadioButton) radioGroup.getChildAt(position)).setTextColor(Color.rgb((int) ((114 - 03) *positionOffset + 03), (int) ((114 - 169) *positionOffset + 169), (int) ((114 - 244) *positionOffset + 244)));
        mView.setX(width * (position + positionOffset));
//        Log.e("OMN","position1=========================================" + position);
    }

    @Override
    public void onPageSelected(int position) {
//        Log.e("TOP","POSITION2==============================================" + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
       /* Log.e("TOP","POSITION3==============================================" + mViewpager.getCurrentItem());
        if(state == ViewPager.SCROLL_STATE_IDLE) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                if (mViewpager.getCurrentItem() != i) {
                    ((RadioButton) radioGroup.getChildAt(mViewpager.getCurrentItem())).setTextColor(Color.rgb(114, 114, 114));
                }
            }
        }*/
    }

    private class FragemntAdapter extends FragmentPagerAdapter {
        public FragemntAdapter(FragmentManager fm) {
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
