package com.divyanshgoenka.accedomatchinggame.singleton;

import com.divyanshgoenka.accedomatchinggame.models.Card;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public class CurrentGame {

    private Card[][] cardSet;

    private CurrentGame(){}

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

    public static CurrentGame getInstance(){
        if(instance == null)
            instance = new CurrentGame();
        return instance;
    }

    public void modifyScore(int modify){
        currentScore += modify;
        if(currentScoreObserver!=null)
            currentScoreObserver.onScoreModified(currentScore);
    }

    public void notifyComplete(){
        if(currentScoreObserver!=null)
            currentScoreObserver.onGameComplete();
    }

    public Card[][] getCardSet() {
        if(cardSet==null)
        cardSet = Card.Utils.newCardSet();
        return cardSet;
    }

    public boolean isComplete() {
        for(int i=0;i<DEFAULT_SIDE;i++){
            for(int j=0;j<DEFAULT_SIDE;j++){
                if(!cardSet[i][j].isRevealed) return false;
            }
        }
        return true;
    }

    public void reset() {
        cardSet = Card.Utils.newCardSet();
        currentScore = 0;

    }
}
