package io.xujiaji.sample;

import android.app.Application;
import android.content.Context;

/**
 * Created by jiana on 16-11-21.
 */

public class App extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
    }

    public static Context getInstanse() {
        return mContext;
    }
}
