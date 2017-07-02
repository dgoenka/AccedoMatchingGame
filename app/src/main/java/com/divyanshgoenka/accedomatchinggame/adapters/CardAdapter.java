package com.divyanshgoenka.accedomatchinggame.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

    public CardAdapter(Card[][] cards) {
        this.cards = cards;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new CardViewHolder(layoutInflater.inflate(R.layout.card, parent, false));
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
