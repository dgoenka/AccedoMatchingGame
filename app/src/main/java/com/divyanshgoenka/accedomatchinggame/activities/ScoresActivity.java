package com.divyanshgoenka.accedomatchinggame.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.divyanshgoenka.accedomatchinggame.AccedoMatchingGameApplication;
import com.divyanshgoenka.accedomatchinggame.R;
import com.divyanshgoenka.accedomatchinggame.models.Score;
import com.divyanshgoenka.accedomatchinggame.util.Validations;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoresActivity extends AppCompatActivity {

    private static final String SCORES = "SCORES";
    private static final String TAG = "ScoresActivity";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.no_scores)
    View no_scores;

    List<Score> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if(savedInstanceState!=null)
            setFromBundle(savedInstanceState);
        else
            new GetSetScoreTable().execute();
    }

    public void setFromBundle(Bundle fromBundle) {
        String json = fromBundle.getString(SCORES);
        Type scoresArrayList  = new TypeToken<ArrayList<Score>>() {
        }.getType();
        scores = new Gson().fromJson(json,scoresArrayList);
        recyclerView.setAdapter(new Score.Adapter(ScoresActivity.this,scores));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SCORES, new Gson().toJson(scores));
    }

    public void setView(List<Score> scores){
        Log.e(TAG,"scores is "+scores);
        if(Validations.isEmptyOrNull(scores)){
            recyclerView.setVisibility(View.GONE);
            no_scores.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            no_scores.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new Score.Adapter(ScoresActivity.this,scores));
        }
    }

    class  GetSetScoreTable extends AsyncTask<Void,Void,List<Score>>{

        @Override
        protected List<Score> doInBackground(Void... params) {
            return AccedoMatchingGameApplication.getInstance().getAppDatabase().scoreDao().getAllScores();
        }

        public void onPostExecute(List<Score> scores){
            setView(scores);
        }
    }
}
