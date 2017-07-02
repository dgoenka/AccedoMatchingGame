package com.divyanshgoenka.accedomatchinggame.utils;

import com.divyanshgoenka.accedomatchinggame.models.Card;
import com.divyanshgoenka.accedomatchinggame.util.Constants;

import java.util.Collections;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

/**
 * Created by divyanshgoenka on 02/07/17.
 */
public class CardUtils {
    public static Card[][] newCardSet() {

        Collections.shuffle(Constants.CARD_SET);
        Card[][] cards = new Card[DEFAULT_SIDE][DEFAULT_SIDE];

        int positionInArr = 0;

        for (int i = 0; i < DEFAULT_SIDE; i++) {
            for (int j = 0; j < DEFAULT_SIDE; j++) {
                cards[i][j] = new Card();
                cards[i][j].value = Constants.CARD_SET.get(positionInArr++);
                cards[i][j].x = i;
                cards[i][j].y = j;
            }
        }

        return cards;
    }
}
