package com.divyanshgoenka.accedomatchinggame.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.divyanshgoenka.accedomatchinggame.R;
import com.divyanshgoenka.accedomatchinggame.models.Card;
import com.divyanshgoenka.accedomatchinggame.viewholders.CardViewHolder;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

    Card[][] cards;
    private DisplayMetrics displaymetrics;

    public CardAdapter(Card[][] cards, DisplayMetrics displayMetrics) {
        this.cards = cards;
        this.displaymetrics = displayMetrics;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View card = layoutInflater.inflate(R.layout.card, parent, false);

        int ht = displaymetrics.heightPixels - parent.getContext().getResources().getDimensionPixelSize(R.dimen.card_height_space_to_leave);
        int wt = displaymetrics.widthPixels;

        card.getLayoutParams().height = ht / DEFAULT_SIDE;
        card.getLayoutParams().width = wt / DEFAULT_SIDE;
        return new CardViewHolder(card);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.bind(cards[position / DEFAULT_SIDE][position % DEFAULT_SIDE], position);
    }

    @Override
    public void onViewRecycled(CardViewHolder holder) {
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        return DEFAULT_SIDE * DEFAULT_SIDE;
    }

}
