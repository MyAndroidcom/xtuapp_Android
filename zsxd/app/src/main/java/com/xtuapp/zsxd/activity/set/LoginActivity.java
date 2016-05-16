package com.xtuapp.zsxd.activity.set;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.xtuapp.zsxd.GlobalConstant;
import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.base.BaseActivity;
import com.xtuapp.zsxd.application.APP;
import com.xtuapp.zsxd.dialog.LoadingDialog;
import com.xtuapp.zsxd.domain.User;
import com.xtuapp.zsxd.utils.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import static com.xtuapp.zsxd.utils.PrefUtils.put;


/**
 * Created by Administrator on 2016/3/31 0031.
 */
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.user_name)
    private EditText userNameEditText;
    @ViewInject(R.id.user_code)
    private EditText userCodeEditText;
    private LoadingDialog mDialog;
    private String username;
    private String password;


    @Override
    protected int setView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mDialog = new LoadingDialog(this);
    }

    @Event(value = R.id.iv_back, type = View.OnClickListener.class)
    private void onClick_back(View view) {
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceStatet) {

    }

    @Event(value = R.id.login, type = View.OnClickListener.class)
    private void onClickLogin(View view) {
        Editable userNameContent = userNameEditText.getText();
        Editable userCodeContent = userCodeEditText.getText();
        if (TextUtils.isEmpty(userNameContent)) {
//            Log.e("LOG","用户名==========================");
            toast("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(userCodeContent)) {
            toast("密码不能为空");
            return;
        }
//        put(this,"string1",userNameContent);
//        put(this,"string2",userCodeContent);
        mDialog.show();

        //使用xutils发送请求
        username = userNameContent.toString();
        password = userCodeContent.toString();
        String loginUrl = String.format(GlobalConstant.Url.LOGIN_URL, username);
        Log.e("LLLL", "loginUrl=========================" + loginUrl);
        RequestParams params = new RequestParams(GlobalConstant.Url.SERVER_URL + loginUrl);
        params.addQueryStringParameter("password", PrefUtils.encrypt(password, username));
        params.addQueryStringParameter("rid", PrefUtils.encrypt(loginUrl + "&" + PrefUtils.uuid(), username));
        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {

                Log.e("LOGIN", "result==================" + result);
                parseData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("LOGIN", "访问失败！！！" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                mDialog.dismiss();
            }
        });
    }

    private void parseData(String result) {
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(result);
            int code = jsonObject.getInt("code");
            if (code == 200) {
                toast("恭喜你登陆成功！！！");
                JSONObject data = jsonObject.getJSONObject("data");
                put(this, "string1", PrefUtils.encrypt(data.toString(),GlobalConstant.Constant.KEY_PASS));
                APP.mUser = gson.fromJson(data.toString(),User.class);
                mDialog.dismiss();
                finish();
            } else {
                String desc = jsonObject.getString("desc");
                toast(desc);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
