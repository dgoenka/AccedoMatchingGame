package com.divyanshgoenka.accedomatchinggame.util;

import com.divyanshgoenka.accedomatchinggame.models.Score;

import java.util.Collection;
import java.util.List;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public class Validations {
    public static boolean isEmptyOrNull(Collection collection) {
        return collection==null||collection.size()<1;
    }
}
