package com.example.model.strategies;

import com.example.model.Card;

import java.io.Serializable;

public class StrategyOne extends Strategy implements Serializable {

    @Override
    public Card play() {
        Card card = null;
        if (table.isPlayerAFirst()) {
            for (Card item : hand) {
                if (card == null) {
                    card = item;
                } else if (item.getRank().getPoints() > card.getRank().getPoints()){
                    card = item;
                }
            }
            hand.remove(card);
            return card;
        } else {
            for (Card item : hand) {
                if (item.getSuit() != table.getTrumpSuit() && item.getSuit() != table.getCardB().getSuit()) {
                    if (card == null) {
                        card = item;
                    } else if (item.getRank().getPoints() > card.getRank().getPoints()){
                        card = item;
                    }
                }
            }
            hand.remove(card);
            if (card != null) return card;
        }
        return hand.remove((int) (Math.random() * hand.size()));
    }
}
