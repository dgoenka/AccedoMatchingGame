package com.divyanshgoenka.accedomatchinggame.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.divyanshgoenka.accedomatchinggame.singleton.CurrentGame;
import com.divyanshgoenka.accedomatchinggame.singleton.CurrentScoreObserver;
import com.divyanshgoenka.accedomatchinggame.singleton.CurrentSelection;
import com.divyanshgoenka.accedomatchinggame.util.Constants;
import com.divyanshgoenka.accedomatchinggame.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.divyanshgoenka.accedomatchinggame.util.Constants.DEFAULT_SIDE;

/**
 * Created by divyanshgoenka on 23/05/17.
 */

public class Card {

    public int x;
    public int y;
    public int value;
    public boolean isRevealed = false;

    public CardObserver getCardObserver() {
        return cardObserver;
    }

    public void setCardObserver(CardObserver cardObserver) {
        this.cardObserver = cardObserver;
    }

    private CardObserver cardObserver;

    public void notifyReset() {
        if(cardObserver!=null)
            cardObserver.notifyReset();
    }

    public static class Utils {
        public static Card[][] newCardSet(){

            Collections.shuffle(Constants.CARD_SET);
            Card[][]  cards = new Card[DEFAULT_SIDE][DEFAULT_SIDE];

            int positionInArr = 0;

            for(int i = 0; i< DEFAULT_SIDE; i++) {
                for (int j = 0; j < DEFAULT_SIDE; j++) {
                    cards[i][j] = new Card();
                    cards[i][j].value = Constants.CARD_SET.get(positionInArr++);
                    cards[i][j].x=i;
                    cards[i][j].y=j;
                }
            }

            return cards;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements CardObserver{

        @BindView(R.id.card_image_view)
        ImageView cardImageView;
        Card cardObj;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Card card) {
            Picasso.with(itemView.getContext()).load(card.isRevealed?card.value:R.drawable.card_bg).into(cardImageView);
            cardObj = card;
            cardObj.setCardObserver(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(!cardObj.isRevealed) {
                       Picasso.with(itemView.getContext()).load(cardObj.value).into(cardImageView);
                       cardObj.isRevealed = true;
                       CurrentSelection.getInstance().getSelections().add(cardObj);
                       if(CurrentSelection.getInstance().verify()){
                           if(CurrentGame.getInstance().isComplete())
                               CurrentGame.getInstance().getCurrentScoreObserver().onGameComplete();
                       }
                   }else{

                   }
                }
            });

        }

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

    /**
     * Created by divyanshgoenka on 23/05/17.
     */

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {

        Card[][] cards;
        LayoutInflater layoutInflater;

        public Adapter(Context context, Card[][] cards) {
            layoutInflater = LayoutInflater.from(context);
            this.cards = cards;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(layoutInflater.inflate(R.layout.card,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(cards[position/ DEFAULT_SIDE][position % DEFAULT_SIDE]);
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            holder.unbind();
        }

        @Override
        public int getItemCount() {
            return DEFAULT_SIDE * DEFAULT_SIDE;
        }
    }
}
