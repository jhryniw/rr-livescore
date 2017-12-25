package ca.ftcalberta.rrlivescore;

import android.app.Application;

import ca.ftcalberta.rrlivescore.models.Settings;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize settings
        Settings.getInstance(this);
    }
}
