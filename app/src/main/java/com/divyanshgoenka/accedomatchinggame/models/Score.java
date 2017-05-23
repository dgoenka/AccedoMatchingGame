package com.divyanshgoenka.accedomatchinggame.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.divyanshgoenka.accedomatchinggame.R;
import com.divyanshgoenka.accedomatchinggame.util.Validations;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

/**
 * Created by divyanshgoenka on 23/05/17.
 */
@Entity
public class Score implements Serializable {
    public @PrimaryKey String id;
    public String name;
    public long time;
    public long timeStamp;

    public static class ViewHolder extends RecyclerView.ViewHolder implements AdapterBindUnbind<Score>{

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.score)
        TextView score;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @Override
        public void bind(Score score) {
            name.setText(score.name);
            name.setText(""+score);
        }

        @Override
        public void unbind() {

        }
    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {

        List<Score> scoreList;
        LayoutInflater layoutInflater;

        public Adapter(Context context, List<Score> scores) {
            layoutInflater = LayoutInflater.from(context);
            this.scoreList = scores;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(layoutInflater.inflate(R.layout.score,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(scoreList.get(position));
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            holder.unbind();
        }

        @Override
        public int getItemCount() {
            return Validations.isEmptyOrNull(scoreList)?0:scoreList.size();
        }
    }
}

