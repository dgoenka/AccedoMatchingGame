package com.divyanshgoenka.accedomatchinggame.activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    private ScoreInserter scoreListener;

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
        score.setText(String.format(getString(R.string.score), newScore));
    }

    @Override
    public void onGameComplete() {
        showScoreDialog();
    }

    public void showScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.enter_your_name).setView(R.layout.score_edit_text).setPositiveButton(R.string.okay, (dialog, which) -> {
            EditText taskEditText = (EditText) ((AlertDialog) dialog).findViewById(R.id.score_edit_text);
            final Score score1 = new Score();
            score1.name = taskEditText.getText().toString();
            score1.time = CurrentGame.getInstance().getCurrentScore();
            score1.timeStamp = System.currentTimeMillis();
            CurrentGame.getInstance().reset();
            scoreListener = new ScoreInserter(score1, (result) -> startScoreActivity());
            scoreListener.execute();
        }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                startNewGame();
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
        Card[][] card = CurrentGame.getInstance().getCardSet();
        Card.Adapter cardAdapter = new Card.Adapter(card);
        recyclerView.setAdapter(cardAdapter);
    }

    private void startScoreActivity() {
        startActivity(new Intent(this, ScoresActivity.class));
    }

}
