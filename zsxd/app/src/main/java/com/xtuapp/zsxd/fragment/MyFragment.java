package com.xtuapp.zsxd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.set.AdviceActivity;
import com.xtuapp.zsxd.activity.set.HistoryActivity;
import com.xtuapp.zsxd.activity.set.LoginActivity;
import com.xtuapp.zsxd.activity.set.MyMessageActivity;
import com.xtuapp.zsxd.activity.set.MyStorageActivity;
import com.xtuapp.zsxd.activity.set.SettingActivity;
import com.xtuapp.zsxd.activity.set.ShopActivity;
import com.xtuapp.zsxd.adapter.SimpleAdapter;
import com.xtuapp.zsxd.adapter.ViewHolder;
import com.xtuapp.zsxd.fragment.base.BaseFragment;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public class MyFragment extends BaseFragment {
    private View view;
    @ViewInject(R.id.lv_my)
    private ListView mListview;
    @ViewInject(R.id.rl_login)
    private RelativeLayout rl_login;

    @Override
    public int setView() {
        return R.layout.activity_setting;
    }

    private int[] imItems = {R.drawable.ic_message_blue_300_24dp, R.drawable.ic_storage_blue_300_24dp,
            R.drawable.ic_history_blue_300_24dp, R.drawable.ic_settings_blue_300_24dp,
            R.drawable.ic_view_headline_blue_300_24dp, R.drawable.ic_shopping_cart_blue_300_24dp};
    private String[] teItems = {"我的发帖","我的收藏","浏览历史","设置","反馈","商城"};
//    private List<BaseActivity> mList = new ArrayList<>();
    private Class<?>[] mClass =
            {MyMessageActivity.class,MyStorageActivity.class,HistoryActivity.class,
            SettingActivity.class, AdviceActivity.class,ShopActivity.class};
    @Override
    public void initView(Bundle savedInstanceState) {
        //设置ListView的点击事件
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("PPP","position ====================" + position);
//                Log.e("PPP","position ====================" + mClass.length);

                if(position == mClass.length - 1){
                   toast("项目招标中，敬请期待...");
                }else if (position != mClass.length - 1){
                    Intent intent = new Intent(getContext(), mClass[position]);
                    startActivity(intent);
                }
            }
        });
    }

    @Event(value = R.id.rl_login,
            type = View.OnClickListener.class/*可选参数, 默认是View.OnClickListener.class*/)
    private void onClick(View view) {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mListview.setAdapter(new SimpleAdapter<String>(getContext(), imItems.length, R.layout.item_my) {
            @Override
            public void convert(ViewHolder viewHolder, View converView, String item, int position) {
                ImageView imageView = viewHolder.getView(R.id.iv_my);
                TextView  textView = viewHolder.getView(R.id.tv_my);
                View viewDivider = viewHolder.getView(R.id.vi_my);
                imageView.setImageResource(imItems[position]);
                textView.setText(teItems[position]);
                if(position == 1 ||  position ==3){
                    viewDivider.setVisibility(View.VISIBLE);
                }else{
                    viewDivider.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
