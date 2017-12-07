package com.example.kazu.recordmanager;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by kazu on 2017/12/06.
 */

public class MyApplication extends Application {
    @Override
    public  void onCreate(){
        super.onCreate();
        Realm.init(this);
    }
}
