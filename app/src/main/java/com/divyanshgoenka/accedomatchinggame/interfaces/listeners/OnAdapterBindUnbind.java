package com.divyanshgoenka.accedomatchinggame.interfaces.listeners;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public interface OnAdapterBindUnbind<T> {
    void bind(T t, int position);

    void unbind();
}
