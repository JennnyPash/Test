package com.jenny.myhome;

import android.app.Application;
import android.content.Context;

/**
 * Created by JennyPash on 1/11/2017.
 */

public class MyHomeApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
