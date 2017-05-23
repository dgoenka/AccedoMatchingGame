package com.divyanshgoenka.accedomatchinggame.models;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public interface AdapterBindUnbind<T> {
    void bind(T t);
    void unbind();
}
