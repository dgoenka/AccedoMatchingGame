package com.divyanshgoenka.accedomatchinggame.repositories;

import android.os.AsyncTask;

import com.divyanshgoenka.accedomatchinggame.AccedoMatchingGameApplication;
import com.divyanshgoenka.accedomatchinggame.interfaces.listeners.OnDbOperationComplete;
import com.divyanshgoenka.accedomatchinggame.models.Score;

import java.util.List;

/**
 * Created by divyanshgoenka on 21/06/17.
 */
public class GetSetScoreTable extends AsyncTask<Void, Void, List<Score>> {


    OnDbOperationComplete<List<Score>> onDbOperationComplete;

    public GetSetScoreTable(OnDbOperationComplete<List<Score>> onDbOperationComplete) {
        this.onDbOperationComplete = onDbOperationComplete;
    }

    @Override
    protected List<Score> doInBackground(Void... params) {
        return AccedoMatchingGameApplication.getInstance().getAppDatabase().scoreDao().getAllScores();
    }

    public void onPostExecute(List<Score> scores) {
        if (onDbOperationComplete != null) onDbOperationComplete.onDbOperationComplete(scores);
    }

    public void register(OnDbOperationComplete<List<Score>> onDbOperationComplete) {
        this.onDbOperationComplete = onDbOperationComplete;
    }

    public void unregister() {
        onDbOperationComplete = null;
    }
}
