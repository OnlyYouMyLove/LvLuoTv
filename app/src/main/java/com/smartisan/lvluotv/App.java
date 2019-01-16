package com.smartisan.lvluotv;

import android.app.Application;

import com.smartisan.lvluotv.component.DaggerNetTaskComponent;
import com.smartisan.lvluotv.component.NetTaskComponent;

public class App extends Application {

    private static NetTaskComponent mNetTaskComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetTaskComponent = DaggerNetTaskComponent.builder().build();
    }

    public static NetTaskComponent getNetTaskComponent() {
        return mNetTaskComponent;
    }
}

