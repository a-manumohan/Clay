package com.clay.clay;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.clay.clay.db.DatabaseManager;

/**
 * Created by manuMohan on 15/05/09.
 */
public class ClayApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        DatabaseManager.createRootUserIfNotPresent();
    }
}
