package com.divyanshgoenka.accedomatchinggame.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.divyanshgoenka.accedomatchinggame.R;
import com.divyanshgoenka.accedomatchinggame.models.Score;
import com.divyanshgoenka.accedomatchinggame.util.Validations;
import com.divyanshgoenka.accedomatchinggame.viewholders.ScoreViewHolder;

import java.util.List;

/**
 * Created by divyanshgoenka on 02/07/17.
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreViewHolder> {

    List<Score> scoreList;
    LayoutInflater layoutInflater;

    public ScoreAdapter(Context context, List<Score> scores) {
        layoutInflater = LayoutInflater.from(context);
        this.scoreList = scores;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScoreViewHolder(layoutInflater.inflate(R.layout.score, parent, false));
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        holder.bind(scoreList.get(position), position);
    }

    @Override
    public void onViewRecycled(ScoreViewHolder holder) {
        holder.unbind();
    }


    @Override
    public int getItemCount() {
        return Validations.isEmptyOrNull(scoreList) ? 0 : scoreList.size();
    }
}
