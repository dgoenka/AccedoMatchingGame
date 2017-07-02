package com.divyanshgoenka.accedomatchinggame.repositories;

import android.os.AsyncTask;

import com.divyanshgoenka.accedomatchinggame.AccedoMatchingGameApplication;
import com.divyanshgoenka.accedomatchinggame.interfaces.listeners.OnDbOperationComplete;
import com.divyanshgoenka.accedomatchinggame.models.Score;

/**
 * Created by divyanshgoenka on 13/06/17.
 */
public class ScoreInserter extends AsyncTask<Void, Void, Void> {

    private final Score score;
    private OnDbOperationComplete<Void> onScoreInsertComplete;

    public ScoreInserter(Score score, OnDbOperationComplete<Void> onScoreInsertComplete) {
        this.score = score;
        this.onScoreInsertComplete = onScoreInsertComplete;
    }

    @Override
    protected Void doInBackground(Void... params) {
        AccedoMatchingGameApplication.getInstance().getAppDatabase().scoreDao().insert(score);
        return null;
    }

    public void onPostExecute(Void ignored) {
        if (onScoreInsertComplete != null)
            onScoreInsertComplete.onDbOperationComplete(ignored);
    }

    public void destroy() {
        onScoreInsertComplete = null;
    }

}
