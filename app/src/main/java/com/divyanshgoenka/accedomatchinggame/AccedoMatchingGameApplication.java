package com.divyanshgoenka.accedomatchinggame;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.divyanshgoenka.accedomatchinggame.database.AppDatabase;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public class AccedoMatchingGameApplication extends Application {

    private static AccedoMatchingGameApplication instance;
    AppDatabase appDatabase;

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "score-database")
                .build();
    }

    public static AccedoMatchingGameApplication getInstance() {
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
