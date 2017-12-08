package com.attendance.courses;

import com.attendance.AttendanceApplication;
import com.attendance.R;
import com.attendance.data.source.remote.CoursesRemoteDataSource;
import com.attendance.util.NetWorkUtil;

/**
 * Created by cristian on 11/18/2017.
 */

public class CoursesPresenter implements CoursesContract.Presenter {

    private final CoursesContract.View view;

    public CoursesPresenter(CoursesContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void getCourseList() {

        Boolean netStatus = new NetWorkUtil().checkNetWorkEx(AttendanceApplication.getContext());
        if (!netStatus) {
            view.showTip(AttendanceApplication.getContext().getString(R.string.network_corrupt));
            view.stopRefresh();
            return;
        }

        CoursesRemoteDataSource.getInstance().getCourseList(new GetCoursesSubscriber(view));

    }

}
