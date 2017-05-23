package com.divyanshgoenka.accedomatchinggame.activities;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.divyanshgoenka.accedomatchinggame.AccedoMatchingGameApplication;
import com.divyanshgoenka.accedomatchinggame.models.Card;
import com.divyanshgoenka.accedomatchinggame.models.Score;
import com.divyanshgoenka.accedomatchinggame.singleton.CurrentGame;
import com.divyanshgoenka.accedomatchinggame.singleton.CurrentScoreObserver;
import com.divyanshgoenka.accedomatchinggame.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity implements CurrentScoreObserver {

    public static final String CARDS_STATE = "CARDS_STATE";

    GridLayoutManager gridLayoutManager;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.score)
    TextView score;

    @BindView(R.id.high_scores)
    View high_scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        gridLayoutManager = new GridLayoutManager(this, DEFAULT_SIDE, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        high_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScoreActivity();
            }
        });

        setup();
    }


    public void onResume(){
        super.onResume();
        CurrentGame.getInstance().setCurrentScoreObserver(this);
    }

    public void onPause(){
        super.onPause();
        CurrentGame.getInstance().setCurrentScoreObserver(null);
    }

    private void setup() {
        Card[][] card = CurrentGame.getInstance().getCardSet();
        recyclerView.setAdapter(new Card.Adapter(this,card));
    }

    @Override
    public void onScoreModified(int newScore) {
        score.setText(String.format(getString(R.string.score), newScore));
    }

    @Override
    public void onGameComplete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText taskEditText = new EditText(this);

        builder.setTitle(R.string.enter_your_name).setView(taskEditText).setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Score score = new Score();
                score.name = taskEditText.getText().toString();
                score.time = CurrentGame.getInstance().getCurrentScore();
                score.timeStamp = System.currentTimeMillis();
                ((AccedoMatchingGameApplication) getApplication()).getAppDatabase().scoreDao().insert(score);
                CurrentGame.getInstance().reset();
                startScoreActivity();
            }
        });
    }

    private void startScoreActivity() {
        startActivity(new Intent(this, ScoresActivity.class));
    }
}
