package com.xtuapp.zsxd.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public class NoScrollViewPager extends ViewPager{
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //表示事件是否拦截，返回false表示不拦截事件

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    //重写OnTouchEvent事件，什么都不做
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
