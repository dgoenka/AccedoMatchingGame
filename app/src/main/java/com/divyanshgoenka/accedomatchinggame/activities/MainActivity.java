package com.divyanshgoenka.accedomatchinggame.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.divyanshgoenka.accedomatchinggame.R;
import com.divyanshgoenka.accedomatchinggame.adapters.CardAdapter;
import com.divyanshgoenka.accedomatchinggame.gameplay.CurrentGame;
import com.divyanshgoenka.accedomatchinggame.interfaces.observers.CurrentScoreObserver;
import com.divyanshgoenka.accedomatchinggame.models.Card;
import com.divyanshgoenka.accedomatchinggame.models.Score;
import com.divyanshgoenka.accedomatchinggame.repositories.ScoreInserter;

import butterknife.BindView;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends BaseActivity implements CurrentScoreObserver {

    public static final String CARDS_STATE = "CARDS_STATE";
    private static final String TAG = "MainActivity";

    GridLayoutManager gridLayoutManager;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private ScoreInserter scoreListener;

    private MenuItem scoreMenuItem;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void register() {
        CurrentGame.getInstance().setCurrentScoreObserver(this);
    }

    @Override
    public void unregister() {
        CurrentGame.getInstance().setCurrentScoreObserver(null);
        if (scoreListener != null)
            scoreListener.destroy();
    }

    @Override
    public void setup(Bundle savedInstanceState) {
        setupRecyclerView();
        startNewGame();
    }


    private void setupRecyclerView() {
        gridLayoutManager = new GridLayoutManager(this, DEFAULT_SIDE, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public void onScoreModified(int newScore) {
        displayScore(newScore);
    }

    public void displayScore(int score) {
        SpannableString s = new SpannableString(String.format(getString(R.string.score), score));
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
        scoreMenuItem.setTitle(s);
    }

    @Override
    public void onGameComplete() {
        showScoreDialog();
    }

    public void showScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View editTextView = View.inflate(this, R.layout.score_edit_text, null);
        TextView messageTextView = (TextView) editTextView.findViewById(R.id.message);
        messageTextView.setText(String.format(getString(R.string.you_scored), CurrentGame.getInstance().getCurrentScore()));

        AlertDialog alertDialog = builder.setCustomTitle(View.inflate(this, R.layout.alert_title, null)).setView(editTextView).setPositiveButton(R.string.okay, (dialog, which) -> {
            EditText taskEditText = (EditText) ((AlertDialog) dialog).findViewById(R.id.score_edit_text);
            final Score score1 = new Score();
            score1.setName(taskEditText.getText().toString());
            score1.setTime(CurrentGame.getInstance().getCurrentScore());
            score1.setTimeStamp(System.currentTimeMillis());
            CurrentGame.getInstance().reset();
            scoreListener = new ScoreInserter(score1, (result) -> gameFinished());
            scoreListener.execute();
        }).show();

    }

    private void gameFinished() {
        startOverNewGame();
        startScoreActivity();
    }

    private void startOverNewGame() {
        startNewGame();
        displayScore(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        this.scoreMenuItem = menu.findItem(R.id.score_item);
        displayScore(CurrentGame.getInstance().getCurrentScore());
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                startOverNewGame();
                break;
            case R.id.highScoresOptions:
                startScoreActivity();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startNewGame() {
        CurrentGame.getInstance().reset();
        Card[][] card = CurrentGame.getInstance().getCardSet();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        CardAdapter cardAdapter = new CardAdapter(card, displaymetrics);
        recyclerView.setAdapter(cardAdapter);
    }

    private void startScoreActivity() {
        startActivity(new Intent(this, ScoresActivity.class));
    }

}
