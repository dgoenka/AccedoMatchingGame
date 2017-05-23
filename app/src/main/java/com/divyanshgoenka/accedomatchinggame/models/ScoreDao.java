package com.divyanshgoenka.accedomatchinggame.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by divyanshgoenka on 23/05/17.
 */
@Dao
public interface ScoreDao {
    @Insert
    public void insert(Score score);

    @Query("select * from score order by time asc")
    public List<Score> getAllScores();

    @Query("delete from score")
    public void deleteAll();
}
