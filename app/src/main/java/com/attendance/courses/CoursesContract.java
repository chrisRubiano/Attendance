package com.attendance.courses;

import com.attendance.BasePresenter;
import com.attendance.BaseView;

/**
 * Created by cristian on 11/18/2017.
 */

public interface CoursesContract {

    interface View extends BaseView<Presenter> {

        void showTip(String tip);

        void startRefresh();

        void stopRefresh();

        void getCourseSuccess();

    }

    interface Presenter extends BasePresenter {

        void getCourseList();

    }

}
