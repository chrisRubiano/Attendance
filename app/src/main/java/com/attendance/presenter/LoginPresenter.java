package com.attendance.presenter;

import com.attendance.AttendanceApplication;
import com.attendance.contract.LoginContract;
import com.attendance.entities.ConstParameter;
import com.attendance.http.HttpMethods;
import com.attendance.subscribers.LoginSubscriber;
import com.attendance.utils.AESUtil;
import com.attendance.utils.NetWorkUtil;
import com.attendance.utils.SharedFileUtil;

/**
 * Created by peiqin on 3/4/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void login(String username, String password, boolean isTeacher, boolean isRemPassword) {

        if ("".compareTo(username) == 0 || "".compareTo(password) == 0) {
            view.showTip(ConstParameter.LOGIN_WARNING);
            return;
        }

        Boolean netStatus = new NetWorkUtil().checkNetWorkEx(AttendanceApplication.getContext());
        if (!netStatus) {
            view.showTip(ConstParameter.NETWORK_CORRUPT);
            return;
        }

        writeData(username, password, isTeacher, isRemPassword);
        HttpMethods.getInstance().login(new LoginSubscriber(view), username, password, isTeacher);

    }

    private void writeData(String username, String password, boolean isTeacher, boolean isRemPassword) {
        SharedFileUtil sharedFileUtil = new SharedFileUtil();
        sharedFileUtil.putBoolean("hasLogin", true);
        sharedFileUtil.putString("username", username);
        if (isRemPassword) {
            //加密后再将密码放入SharePreferences
            String password_after_encrypt = AESUtil.encryptWithBase64(password);
            sharedFileUtil.putString("password", password_after_encrypt);
            sharedFileUtil.putBoolean("isRemPassword", true);
        }
        sharedFileUtil.putBoolean("isTeacher", isTeacher);
    }

}