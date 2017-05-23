package com.divyanshgoenka.accedomatchinggame;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.divyanshgoenka.accedomatchinggame.database.AppDatabase;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public class AccedoMatchingGameApplication extends Application {

    AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "score-database")
                .build();
    }
}
