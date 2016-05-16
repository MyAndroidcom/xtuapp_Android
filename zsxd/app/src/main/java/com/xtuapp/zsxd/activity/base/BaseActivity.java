package com.xtuapp.zsxd.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.xutils.x;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected abstract int setView();
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initData(Bundle savedInstanceStatet);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setView());
        x.view().inject(this);
        initView(savedInstanceState);
        initData(savedInstanceState);
    }
    protected void toast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    };
}
