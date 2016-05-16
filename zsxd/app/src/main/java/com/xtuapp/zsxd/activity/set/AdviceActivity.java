package com.xtuapp.zsxd.activity.set;

import android.os.Bundle;
import android.view.View;

import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.base.BaseActivity;

import org.xutils.view.annotation.Event;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class AdviceActivity extends BaseActivity{
    @Override
    protected int setView() {
        return R.layout.activity_advice;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceStatet) {

    }
    @Event(value = R.id.iv_back,type = View.OnClickListener.class)
    private void OnClick_back(View view){
        finish();
    }
}
