package com.divyanshgoenka.accedomatchinggame.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by divyanshgoenka on 23/05/17.
 */
@Entity
public class Score {
    public @PrimaryKey String id;
    public String name;
    public long time;
    public long timeStamp;
}
