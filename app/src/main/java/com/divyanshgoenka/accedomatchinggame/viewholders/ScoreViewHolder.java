package com.divyanshgoenka.accedomatchinggame.viewholders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.divyanshgoenka.accedomatchinggame.AccedoMatchingGameApplication;
import com.divyanshgoenka.accedomatchinggame.R;
import com.divyanshgoenka.accedomatchinggame.models.AdapterBindUnbind;
import com.divyanshgoenka.accedomatchinggame.models.Score;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by divyanshgoenka on 02/07/17.
 */
public class ScoreViewHolder extends RecyclerView.ViewHolder implements AdapterBindUnbind<Score> {

    @BindView(R.id.name)
    TextView nameTT;

    @BindView(R.id.score)
    TextView scoreTT;


    public ScoreViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    @Override
    public void bind(Score score, int position) {
        nameTT.setText(score.getName());
        scoreTT.setText("" + score.getTime());
        itemView.setBackgroundColor(position % 2 == 0 ? Color.TRANSPARENT : AccedoMatchingGameApplication.getInstance().getResources().getColor(R.color.grey));

    }


    @Override
    public void unbind() {
        itemView.setBackgroundColor(Color.TRANSPARENT);
    }
}
