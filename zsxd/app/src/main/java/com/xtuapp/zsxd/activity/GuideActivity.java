package com.xtuapp.zsxd.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.base.BaseActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
     @ViewInject(R.id.vp_guide)
     private ViewPager viewPager;
    private  final int[] imageIds= {R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};
    private ArrayList<ImageView> imageList;
    @ViewInject(R.id.bt_star)
    private Button mbutton;
    @Override
    protected int setView() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        imageList = new ArrayList<>();
        //放三个导航页
        for(int i = 0;i < imageIds.length;i++){
            ImageView image = new ImageView(this);
            image.setBackgroundResource(imageIds[i]);
            imageList.add(image);
        }
        viewPager.setAdapter(new GuideApater());
        viewPager.addOnPageChangeListener(this);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mbutton.setVisibility(View.VISIBLE);
                startActivity(new Intent(GuideActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.e("GUIDE","SATART==============================");
        if(position == imageIds.length - 1){
            //最后一个页面
            mbutton.setVisibility(View.VISIBLE);//显示开始体验按钮
        }else{
            mbutton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class GuideApater extends PagerAdapter {
        @Override
        public int getCount() {
            return imageIds.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageList.get(position));
            return imageList.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
        }
    }
}
