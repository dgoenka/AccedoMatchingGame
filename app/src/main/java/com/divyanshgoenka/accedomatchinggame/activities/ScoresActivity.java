package com.divyanshgoenka.accedomatchinggame.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.divyanshgoenka.accedomatchinggame.R;
import com.divyanshgoenka.accedomatchinggame.adapters.ScoreAdapter;
import com.divyanshgoenka.accedomatchinggame.models.Score;
import com.divyanshgoenka.accedomatchinggame.repositories.GetSetScoreTable;
import com.divyanshgoenka.accedomatchinggame.util.Validations;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScoresActivity extends BaseActivity {

    private static final String SCORES = "SCORES";
    private static final String TAG = "ScoresActivity";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.no_scores)
    View no_scores;

    List<Score> scores;

    GetSetScoreTable getSetScoreTable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scores;
    }

    @Override
    protected void setup(Bundle savedInstanceState) {
        setupScoreTable(savedInstanceState);
        setupTitleBar();
    }

    public void setupScoreTable(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            setFromBundle(savedInstanceState);
        } else {
            getSetScoreTable = new GetSetScoreTable((scores) -> setView(scores));
            getSetScoreTable.execute();
        }
    }

    public void setupTitleBar() {
        TextView title = getActionBarTextView();
        title.setAllCaps(true);
        title.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void register() {
        if (getSetScoreTable != null)
            getSetScoreTable.register((scores) -> setView(scores));
    }

    @Override
    protected void unregister() {
        if (getSetScoreTable != null)
            getSetScoreTable.unregister();
    }

    public void setFromBundle(Bundle fromBundle) {
        String json = fromBundle.getString(SCORES);
        Type scoresArrayList = new TypeToken<ArrayList<Score>>() {
        }.getType();
        scores = new Gson().fromJson(json, scoresArrayList);
        recyclerView.setAdapter(new ScoreAdapter(ScoresActivity.this, scores));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SCORES, new Gson().toJson(scores));
    }

    public void setView(List<Score> scores) {
        Log.e(TAG, "scores is " + scores);
        if (Validations.isEmptyOrNull(scores)) {
            recyclerView.setVisibility(View.GONE);
            no_scores.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            no_scores.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new ScoreAdapter(ScoresActivity.this, scores));
        }
    }

}
