package com.divyanshgoenka.accedomatchinggame.gameplay;

import com.divyanshgoenka.accedomatchinggame.interfaces.observers.CurrentScoreObserver;
import com.divyanshgoenka.accedomatchinggame.models.Card;
import com.divyanshgoenka.accedomatchinggame.utils.CardUtils;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public class CurrentGame {

    private static final String TAG = "CurrentGame";
    private Card[][] cardSet;
    public int completedCards = 0;

    private CurrentGame() {
    }

    private static CurrentGame instance;

    public int getCurrentScore() {
        return currentScore;
    }

    private int currentScore = 0;

    public CurrentScoreObserver currentScoreObserver;

    public CurrentScoreObserver getCurrentScoreObserver() {
        return currentScoreObserver;
    }

    public void setCurrentScoreObserver(CurrentScoreObserver currentScoreObserver) {
        this.currentScoreObserver = currentScoreObserver;
    }

    public static CurrentGame getInstance() {
        if (instance == null)
            instance = new CurrentGame();
        return instance;
    }

    public void modifyScore(int modify) {
        currentScore += modify;
        if (currentScoreObserver != null)
            currentScoreObserver.onScoreModified(currentScore);
    }

    public void notifyComplete() {
        if (currentScoreObserver != null)
            currentScoreObserver.onGameComplete();
    }

    public Card[][] getCardSet() {
        if (cardSet == null)
            cardSet = CardUtils.newCardSet();
        return cardSet;
    }

    public boolean isComplete() {
        return (completedCards == DEFAULT_SIDE * DEFAULT_SIDE);
    }

    public void reset() {
        cardSet = CardUtils.newCardSet();
        currentScore = 0;
        completedCards = 0;
        CurrentSelection.getInstance().reset();
    }
}
