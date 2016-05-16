package com.xtuapp.zsxd.activity.set;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.base.BaseActivity;
import com.xtuapp.zsxd.adapter.SimpleAdapter;
import com.xtuapp.zsxd.adapter.ViewHolder;
import com.xtuapp.zsxd.dialog.CustomDialog;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.lv_set)
    private ListView mListView;
    @ViewInject(R.id.iv_back)
    private ImageButton mBackBtn;
    @ViewInject(R.id.bt_message)
    private Button mButton;
    private String[] mItems = {"要闻推送", "非Wifi网络流量", "清理缓存", "检查更新", "为掌上湘大评分", "关于"};
    protected int setView() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBackBtn.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("SETTING","position===================" + position);
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        ShowCustomDialog();
                        break;
                    case 5:

                        break;
                    case 6:
                        break;
                }
            }
        });
    }

    private void ShowCustomDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setMessage("觉得掌上湘大还不错？给个好评吧，亲！");
//        builder.setTitle("提示");
        builder.setPositiveButton("残忍拒绝", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //设置你的操作事项
                toast("卸载成功");
            }
        });

        builder.setNegativeButton("去评分",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    toast("安装成功");
                    }
                });

        builder.create().show();
    }

    @Override
    protected void initData(Bundle savedInstanceStatet) {
        mListView.setAdapter(new SimpleAdapter<String>(this,mItems.length,R.layout.item_set) {
            @Override
            public void convert(ViewHolder viewHolder, View converView, String item, int position) {
                TextView textView = viewHolder.getView(R.id.tv_set);
                TextView textViewRight = viewHolder.getView(R.id.tv_title);
                View viewDivider = viewHolder.getView(R.id.vi_set);
                SwitchCompat switchCompat = viewHolder.getView(R.id.sw_view);
                textView.setText(mItems[position]);
                if(position == 1 || position == 3){
                    viewDivider.setVisibility(View.VISIBLE);
                }else {
                    viewDivider.setVisibility(View.INVISIBLE);
                }
                if(position == 3){
                    textViewRight.setVisibility(View.VISIBLE);
                    switchCompat.setVisibility(View.INVISIBLE);
                }else{
                    textViewRight.setVisibility(View.INVISIBLE);
                    switchCompat.setVisibility(View.VISIBLE);
                }
                if(position == 4){
                    switchCompat.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.iv_back:
            finish();
            break;
        case R.id.bt_message:
            startActivity(new Intent(this,AdviceActivity.class));
            break;
    }
    }
}
