package com.divyanshgoenka.accedomatchinggame.database;

import android.os.AsyncTask;

import com.divyanshgoenka.accedomatchinggame.AccedoMatchingGameApplication;
import com.divyanshgoenka.accedomatchinggame.models.Score;

/**
 * Created by divyanshgoenka on 13/06/17.
 */
public class ScoreInserter extends AsyncTask<Void, Void, Void> {

    private final Score score;
    private OnDbOperationComplete onScoreInsertComplete;

    public ScoreInserter(Score score, OnDbOperationComplete onScoreInsertComplete) {
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
            onScoreInsertComplete.onDbOperationComplete();
    }

    public void destroy() {
        onScoreInsertComplete = null;
    }

}
