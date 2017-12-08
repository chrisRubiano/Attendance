package com.attendance.splash;

import com.attendance.BasePresenter;
import com.attendance.BaseView;

/**
 * Created by cristian on 11/18/2017.
 */

public interface SplashContract {

    interface View extends BaseView<Presenter> {

        void startLoginActivity();

        void startMainActivity();

    }

    interface Presenter extends BasePresenter {

        void startTimeTask();

    }

}
