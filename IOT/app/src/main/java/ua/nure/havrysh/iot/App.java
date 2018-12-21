package ua.nure.havrysh.iot;

import android.app.Application;

/**
 * Created by Oleg on 25.12.2016.
 */

public class App extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        Rest.INSTANCE.init("https://thawing-cove-80542.herokuapp.com/");
    }
}
