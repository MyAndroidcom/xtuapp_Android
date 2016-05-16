package com.xtuapp.zsxd.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xtuapp.zsxd.GlobalConstant;
import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.GuideActivity;
import com.xtuapp.zsxd.activity.base.BaseActivity;
import com.xtuapp.zsxd.utils.PrefUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class SplashActivity extends BaseActivity {

    @ViewInject(R.id.rl_splash)
    private RelativeLayout splashImageView;

    Handler handler = new Handler();


    @Override
    protected int setView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        startAnim();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    private void startAnim() {
//        x.image().bind(splashImageView, GlobalConstant.SPLASH_URL);
        //动画集合
        AnimationSet set = new AnimationSet(false);
        //缩进动画
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(500);//动画时间
        scale.setFillAfter(true);//保持动画时间
        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        scale.setDuration(500);//动画时间
        scale.setFillAfter(true);//保持动画时间

        set.addAnimation(scale);
        set.addAnimation(alpha);

        splashImageView.startAnimation(set);
//        Log.e("SPL","Animation==============");
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                jumpNextPage();
//                Log.e("Splash","RUN===============");
            }
        },2000);
    }

    private void jumpNextPage() {
        Intent intent = null;
        if(PrefUtils.get(this,Long.class, GlobalConstant.Pref.IS_FIRST) == -1){
//            Log.e("AAA","GUIDE========================================");
            intent = new Intent(this,GuideActivity.class);
            PrefUtils.put(this,GlobalConstant.Pref.IS_FIRST,System.currentTimeMillis());

        }else {
//            Log.e("BB","HOME==========================================");
            intent = new Intent(this,HomeActivity.class);
        }
        startActivity(intent);
        finish();
    }


}
