package com.divyanshgoenka.accedomatchinggame.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.divyanshgoenka.accedomatchinggame.R;
import com.divyanshgoenka.accedomatchinggame.gameplay.CurrentGame;
import com.divyanshgoenka.accedomatchinggame.gameplay.CurrentSelection;
import com.divyanshgoenka.accedomatchinggame.interfaces.listeners.OnAdapterBindUnbind;
import com.divyanshgoenka.accedomatchinggame.interfaces.observers.CardObserver;
import com.divyanshgoenka.accedomatchinggame.models.Card;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by divyanshgoenka on 02/07/17.
 */
public class CardViewHolder extends RecyclerView.ViewHolder implements CardObserver, OnAdapterBindUnbind<Card> {

    @BindView(R.id.card_image_view)
    ImageView cardImageView;
    Card cardObj;

    public CardViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(final Card card, int position) {
        Picasso.with(itemView.getContext()).load(card.isRevealed ? card.value : R.drawable.card_bg).into(cardImageView);
        cardObj = card;
        cardObj.setCardObserver(this);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cardObj.isRevealed) {
                    Picasso.with(itemView.getContext()).load(cardObj.value).into(cardImageView);
                    cardObj.isRevealed = true;
                    CurrentSelection.getInstance().getSelections().add(cardObj);
                    if (CurrentSelection.getInstance().verifySelection()) {
                        if (CurrentGame.getInstance().isComplete())
                            CurrentGame.getInstance().getCurrentScoreObserver().onGameComplete();
                    }
                } else {

                }
            }
        });

    }

    @Override
    public void unbind() {
        Picasso.with(itemView.getContext()).cancelRequest(cardImageView);
        cardObj.setCardObserver(null);
        cardObj = null;
    }

    @Override
    public void notifyReset() {
        itemView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Picasso.with(itemView.getContext()).load(R.drawable.card_bg).into(cardImageView);
            }
        }, 800);
        cardObj.isRevealed = false;
    }
}
