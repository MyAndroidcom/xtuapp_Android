package com.xtuapp.zsxd.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.widget.TextView;

import com.xtuapp.zsxd.R;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class LoadingDialog extends Dialog {

    private TextView tipTextView;

    public LoadingDialog(@NonNull Context context) {
        this(context, R.style.Dialog_Dark);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_login);
        setCancelable(false);
        initView();
    }

    private void initView() {
        tipTextView = (TextView) findViewById(R.id.tv_tip);
    }

    public void setTip(String text) {
        tipTextView.setText(text);
    }

    public void show(String tip) {
        setTip(tip);
        super.show();
    }
}

