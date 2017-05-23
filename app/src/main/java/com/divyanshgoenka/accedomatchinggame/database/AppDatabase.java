package com.divyanshgoenka.accedomatchinggame.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import com.divyanshgoenka.accedomatchinggame.models.Score;
import com.divyanshgoenka.accedomatchinggame.models.ScoreDao;

/**
 * Created by divyanshgoenka on 23/05/17.
 */
@Database(entities = {Score.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoreDao scoreDao();
}
