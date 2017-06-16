package com.divyanshgoenka.accedomatchinggame.database;

import java.util.Objects;

/**
 * Created by divyanshgoenka on 14/06/17.
 */
public interface OnDbOperationComplete<T> {
    public void onDbOperationComplete(T results);
}
