package com.jenny.myhome;

import android.app.Application;
import android.content.Context;

import com.jenny.database.Database;

/**
 * Created by JennyPash on 1/11/2017.
 */

public class MyHomeApplication extends Application {
    private static Context context;
    private static Database myHomeDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        this.myHomeDatabase = new Database(this);
    }

    public static Context getContext(){
        return context;
    }

    public static Database getDatabase() {
        return myHomeDatabase;
    }
}
