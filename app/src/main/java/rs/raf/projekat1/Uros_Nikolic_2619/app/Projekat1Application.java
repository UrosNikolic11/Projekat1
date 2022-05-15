package rs.raf.projekat1.Uros_Nikolic_2619.app;

import android.app.Application;

import timber.log.Timber;

public class Projekat1Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
