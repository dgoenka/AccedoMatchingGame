package com.divyanshgoenka.accedomatchinggame.activities;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.divyanshgoenka.accedomatchinggame.models.Card;
import com.divyanshgoenka.accedomatchinggame.singleton.CurrentGame;
import com.divyanshgoenka.accedomatchinggame.singleton.CurrentScoreObserver;
import com.divyanshgoenka.accedomatchinggame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity implements CurrentScoreObserver {

    GridLayoutManager gridLayoutManager;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.score)
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        gridLayoutManager = new GridLayoutManager(this, DEFAULT_SIDE, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
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
                CurrentGame.getInstance().reset();
                Room.
            }
        });
    }
}
