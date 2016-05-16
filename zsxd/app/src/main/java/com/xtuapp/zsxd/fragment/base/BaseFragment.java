package com.xtuapp.zsxd.fragment.base;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.xutils.x;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public abstract class BaseFragment extends Fragment{
    public abstract int setView();
    public abstract void initView(Bundle savedInstanceState);
    public abstract void initData(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(setView(),container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        x.view().inject(this, this.getView());
    }

    /* 获取窗体宽高
    */
    private Point getWindowDisplayPoint() {
        Point point = new Point();
        // 获取屏幕信息
        getActivity().getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * 获取窗体宽
     */
    public  int getWindowWidth() {
        return getWindowDisplayPoint().x;
    }

    /**
     * 获取窗体高
     */
    public  int getWindowHeight() {
        return getWindowDisplayPoint().y;
    }

    protected void toast(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    };

}
