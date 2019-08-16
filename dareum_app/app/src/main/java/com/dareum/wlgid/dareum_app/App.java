package com.dareum.wlgid.dareum_app;

import android.app.Application;

import com.dareum.wlgid.dareum_app.core.LockManager;

/**
 * Created by mintie on 2017-05-15.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LockManager.getInstance().enableAppLock(this);
    }

}
