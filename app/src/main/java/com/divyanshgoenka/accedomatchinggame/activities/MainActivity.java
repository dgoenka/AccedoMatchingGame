package com.divyanshgoenka.accedomatchinggame.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.divyanshgoenka.accedomatchinggame.database.ScoreInserter;
import com.divyanshgoenka.accedomatchinggame.models.Card;
import com.divyanshgoenka.accedomatchinggame.models.Score;
import com.divyanshgoenka.accedomatchinggame.singleton.CurrentGame;
import com.divyanshgoenka.accedomatchinggame.singleton.CurrentScoreObserver;
import com.divyanshgoenka.accedomatchinggame.R;

import butterknife.BindView;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends BaseActivity implements CurrentScoreObserver {

    public static final String CARDS_STATE = "CARDS_STATE";

    GridLayoutManager gridLayoutManager;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.score)
    TextView score;

    @BindView(R.id.high_scores)
    View high_scores;
    private ScoreInserter scoreListener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void register(){
        CurrentGame.getInstance().setCurrentScoreObserver(this);
    }

    @Override
    public void unregister(){
        CurrentGame.getInstance().setCurrentScoreObserver(null);
        if(scoreListener!=null)
         scoreListener.destroy();
    }

    @Override
    public void setup(Bundle savedInstanceState) {
        setupRecyclerView();
        high_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScoreActivity();
            }
        });


    }

    private void setupRecyclerView() {
        gridLayoutManager = new GridLayoutManager(this, DEFAULT_SIDE, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        Card[][] card = CurrentGame.getInstance().getCardSet();
        Card.Adapter cardAdapter = new Card.Adapter(card);
        recyclerView.setAdapter(cardAdapter);
    }

    @Override
    public void onScoreModified(int newScore) {
        score.setText(String.format(getString(R.string.score), newScore));
    }

    @Override
    public void onGameComplete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText taskEditText = new EditText(this);

        builder.setTitle(R.string.enter_your_name).setView(taskEditText).setPositiveButton(R.string.okay, (dialog, which) -> {
            final Score score1 = new Score();
            score1.name = taskEditText.getText().toString();
            score1.time = CurrentGame.getInstance().getCurrentScore();
            score1.timeStamp = System.currentTimeMillis();
            CurrentGame.getInstance().reset();
            scoreListener = new ScoreInserter(score1,(result)->startScoreActivity());
            scoreListener.execute();
        }).show();
    }



    private void startScoreActivity() {
        startActivity(new Intent(this, ScoresActivity.class));
    }

}
