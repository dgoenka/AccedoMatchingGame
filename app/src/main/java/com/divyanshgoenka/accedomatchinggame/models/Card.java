package com.divyanshgoenka.accedomatchinggame.models;

import com.divyanshgoenka.accedomatchinggame.interfaces.observers.CardObserver;

import java.io.Serializable;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public class Card implements Serializable {

    public int x;
    public int y;
    public int value;
    public boolean isRevealed = false;


    public void setCardObserver(CardObserver cardObserver) {
        this.cardObserver = cardObserver;
    }

    private CardObserver cardObserver;

    public void notifyReset() {
        if (cardObserver != null)
            cardObserver.notifyReset();
    }

}
