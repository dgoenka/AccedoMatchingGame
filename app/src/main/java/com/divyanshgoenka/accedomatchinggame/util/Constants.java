package com.divyanshgoenka.accedomatchinggame.util;

import com.divyanshgoenka.accedomatchinggame.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public class Constants {

    public static final int DEFAULT_SIDE = 4;
    public static final int MAX_CARDS_NUM = 16;
    public static final List<Integer> CARD_SET = new ArrayList<Integer>(){
        {
            add(R.drawable.colour1);
            add(R.drawable.colour2);
            add(R.drawable.colour3);
            add(R.drawable.colour4);
            add(R.drawable.colour5);
            add(R.drawable.colour6);
            add(R.drawable.colour7);
            add(R.drawable.colour8);
            add(R.drawable.colour1);
            add(R.drawable.colour2);
            add(R.drawable.colour3);
            add(R.drawable.colour4);
            add(R.drawable.colour5);
            add(R.drawable.colour6);
            add(R.drawable.colour7);
            add(R.drawable.colour8);
        }
    };
    public static final int SELECTION_SIZE = 2;
    public static final int NEGATIVE_SCORE = -1;
    public static final int POSITIVE_SCORE = 2;
}
