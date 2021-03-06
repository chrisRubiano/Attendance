package com.attendance.splash;

import com.attendance.util.SharedFileUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cristian on 11/18/2017.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private final SplashContract.View view;

    public SplashPresenter(SplashContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void startTimeTask() {

        long splashDelay = 2000;
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                boolean hasLogin = new SharedFileUtil().getBoolean("hasLogin");
                if (hasLogin) {
                    view.startMainActivity();
                } else {
                    view.startLoginActivity();
                }
            }

        };
        Timer timer = new Timer();
        timer.schedule(task, splashDelay);

    }

}
