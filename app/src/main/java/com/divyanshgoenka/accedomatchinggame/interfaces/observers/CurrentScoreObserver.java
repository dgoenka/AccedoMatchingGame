package com.divyanshgoenka.accedomatchinggame.interfaces.observers;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public interface CurrentScoreObserver {
    void onScoreModified(int newScore);

    void onGameComplete();
}
