package com.divyanshgoenka.accedomatchinggame.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by divyanshgoenka on 23/05/17.
 */
@Entity
public class Score implements Serializable {
    @PrimaryKey
    private String id;
    private String name;
    private long time;
    private long timeStamp;

    @Override
    public String toString() {
        return getName() + " - " + getTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        if (timeStamp > 0)
            this.timeStamp = timeStamp;
        else
            throw new IllegalArgumentException("Invalid timestamp");
    }

}

