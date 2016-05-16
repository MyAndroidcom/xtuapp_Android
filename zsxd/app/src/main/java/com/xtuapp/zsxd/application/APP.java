package com.xtuapp.zsxd.application;

import android.app.Application;

import com.google.gson.Gson;
import com.xtuapp.zsxd.BuildConfig;
import com.xtuapp.zsxd.GlobalConstant;
import com.xtuapp.zsxd.domain.User;
import com.xtuapp.zsxd.utils.PrefUtils;

import org.xutils.x;

/**
 * Created by Administrator on 2016/4/8 0008.
 */
public class APP extends Application{
    public static User mUser;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        String mData = PrefUtils.get(getApplicationContext(),String.class,"string1");
        mData = PrefUtils.decrypt(mData, GlobalConstant.Constant.KEY_PASS);
        if(mData != null){
            Gson gson = new Gson();
            mUser = gson.fromJson(mData,User.class);
        }
    }
}
