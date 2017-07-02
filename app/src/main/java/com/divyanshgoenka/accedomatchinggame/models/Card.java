package com.divyanshgoenka.accedomatchinggame.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.divyanshgoenka.accedomatchinggame.R;
import com.divyanshgoenka.accedomatchinggame.viewholders.CardViewHolder;

import java.io.Serializable;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

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
