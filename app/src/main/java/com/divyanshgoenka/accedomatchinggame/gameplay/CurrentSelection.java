package com.divyanshgoenka.accedomatchinggame.gameplay;

import com.divyanshgoenka.accedomatchinggame.models.Card;
import com.divyanshgoenka.accedomatchinggame.util.Constants;

import java.util.ArrayList;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public class CurrentSelection {
    private static CurrentSelection instance;

    private final ArrayList<Card> selections = new ArrayList<>();

    public ArrayList<Card> getSelections() {
        return selections;
    }

    private CurrentSelection() {
    }

    public static synchronized CurrentSelection getInstance() {
        if (instance == null)
            instance = new CurrentSelection();
        return instance;
    }


    public boolean verifySelection() {
        if (getSelections().size() == Constants.SELECTION_SIZE) {
            int initial = getSelections().get(0).value;
            for (int i = 1; i < getSelections().size(); i++) {
                if (getSelections().get(i).value == initial) {
                    continue;
                } else {
                    CurrentGame.getInstance().modifyScore(Constants.NEGATIVE_SCORE);
                    reset();
                    return false;
                }
            }
            CurrentGame.getInstance().modifyScore(Constants.POSITIVE_SCORE);
            CurrentSelection.getInstance().getSelections().clear();
            CurrentGame.getInstance().completedCards += 2;
            return true;
        }
        return false;
    }

    public void reset() {
        for (int i = 0; i < selections.size(); i++) {
            selections.get(i).notifyReset();
        }
        selections.clear();
    }
}
