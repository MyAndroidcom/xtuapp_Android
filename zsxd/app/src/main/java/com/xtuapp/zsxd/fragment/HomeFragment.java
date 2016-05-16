package com.xtuapp.zsxd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;
import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.home.BookActivity;
import com.xtuapp.zsxd.activity.home.JobActivity;
import com.xtuapp.zsxd.activity.home.LostActivity;
import com.xtuapp.zsxd.activity.home.MapActivity;
import com.xtuapp.zsxd.activity.home.MarKetActivity;
import com.xtuapp.zsxd.activity.home.PlayActivity;
import com.xtuapp.zsxd.activity.home.ScheduleActivity;
import com.xtuapp.zsxd.activity.home.SchoolActivity;
import com.xtuapp.zsxd.activity.home.ScoreActivity;
import com.xtuapp.zsxd.fragment.base.BaseFragment;

import org.xutils.view.annotation.ViewInject;


/**
 * Created by Administrator on 2016/3/28 0028.
 */
public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener{
    @ViewInject(R.id.vp_news)
    private ViewPager mViewPager;
    public int[] mItems = new int[]{R.drawable.home_top_1,R.drawable.home_top_2,R.drawable.home_top_3,
            R.drawable.home_top_4,R.drawable.home_top_5};
    public String[] mTitles = new String[]{"湘大新闻一","湘大新闻二","湘大新闻三","湘大新闻四","湘大新闻五"};
    public String[] mHomeItems = new String[]{"学校简介","成绩查询","失物招领","跳骚市场",
            "兼职信息","游玩指南","校园地图","课表查询","书籍查询"};

    @ViewInject(R.id.tv_title)
    private TextView tvTitle;//头条新闻的标题
    @ViewInject(R.id.indicator)
    private CirclePageIndicator mIndicator;
    @ViewInject(R.id.gv_home)
    private GridView gridView;
//    @ViewInject(R.id.tv_item)
    private TextView itemTitle;
    @Override
    public int setView() {
        return R.layout.fragment_home;
    }
    private Handler mHandler = new Handler() ;

    private  Runnable runnable;
    @Override
    public void initView(Bundle savedInstanceState) {
        tvTitle.setText(mTitles[0]);
//        mViewPager.setOnPageChangeListener(this);
        gridView.setAdapter(new HomeAdapter());
    }

    @Override
    public void initData(Bundle savedInstanceState) {
            getDataFromServer();
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            //学校简介
                            startActivity(new Intent(getContext(), SchoolActivity.class));
                            break;
                        case 1:
                            //成绩查询
                            startActivity(new Intent(getContext(), ScoreActivity.class));
                            break;
                        case 2:
                            //失物招领
                            startActivity(new Intent(getContext(), LostActivity.class));
                            break;
                        case 3:
                            //跳骚市场
                            startActivity(new Intent(getContext(), MarKetActivity.class));
                            break;
                        case 4:
                            //兼职信息
                            startActivity(new Intent(getContext(), JobActivity.class));
                            break;
                        case 5:
                            //游玩指南
                            startActivity(new Intent(getContext(), PlayActivity.class));
                            break;
                        case 6:
                            //校园地图
                            startActivity(new Intent(getContext(), MapActivity.class));
                            break;
                        case 7:
                            startActivity(new Intent(getContext(), ScheduleActivity.class));
                            break;
                        case 8:
                            //书籍查询
                            startActivity(new Intent(getContext(), BookActivity.class));
                            break;
                    }
                }
            });
        mHandler.postDelayed(runnable =  new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(this, 2000);
                mViewPager.setCurrentItem((mViewPager.getCurrentItem() + 1) % mViewPager.getAdapter().getCount());
//                Log.e("HOME","getcount=====================" + mViewPager.getAdapter().getCount());
            }
        }, 2000);

    }

    @Override
    public void onDestroyView() {
        Log.e("HOME", "onDestory+++++++++===============================");
        mHandler.removeCallbacks(runnable);
        super.onDestroyView();
    }

    public void getDataFromServer() {
        mViewPager.setAdapter(new TopNewsAdapter());
//        mViewPager.setPageTransformer(true,new RotateUpTransformer());
        mIndicator.setViewPager(mViewPager);
        mIndicator.setOnPageChangeListener(this);
//        mIndicator.setSnap(true);// 支持快照显示
        mIndicator.onPageSelected(0);// 让指示器重新定位到第一个点
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            tvTitle.setText(mTitles[position]);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class TopNewsAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mItems.length;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView image = new ImageView(getContext());
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(mItems[position]);
//            x.image().bind(image,mItems[position]);
            container.addView(image);
            return image;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
        }
    }

    private class HomeAdapter extends  BaseAdapter {
        @Override
        public int getCount() {
            return mHomeItems.length;
        }

        @Override
        public Object getItem(int position) {
            return mHomeItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getContext(),R.layout.home_list_item,null);
            itemTitle = (TextView) view.findViewById(R.id.tv_item);
            itemTitle.setText(mHomeItems[position]);
            return view;
        }
    }
}
