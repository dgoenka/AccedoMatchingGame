package com.divyanshgoenka.accedomatchinggame.utils;

import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by divyanshgoenka on 22/06/17.
 */

public class AlertDialogUtil {
    public static <T extends View> void setViewProperty(AlertDialog alertDialog, int id, Class<T> type, OnViewFound<T> callback) {
        T view = (T) alertDialog.findViewById(id);
        if (callback != null) callback.onViewFound(view);
    }

    public interface OnViewFound<T extends View> {
        public void onViewFound(T view);
    }
}
