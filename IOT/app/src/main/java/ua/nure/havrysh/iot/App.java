package ua.nure.havrysh.iot;

import android.app.Application;
import android.preference.PreferenceManager;

/**
 * Created by Oleg on 25.12.2016.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Rest.init(PreferenceManager.getDefaultSharedPreferences(this).getString("ip", "http://192.168.43.13:8080/"));
    }
}
