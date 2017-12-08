package com.attendance.login;

import com.attendance.BasePresenter;
import com.attendance.BaseView;

/**
 * Created by cristian on 11/18/2017.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void showTip(String tip);

        void showProgress(String msg);

        void cancelProgress();

        void startMainActivity();

    }

    interface Presenter extends BasePresenter {

        void login(String username, String password, boolean isTeacher, boolean isRemPassword);

        void writeData();

    }

}
