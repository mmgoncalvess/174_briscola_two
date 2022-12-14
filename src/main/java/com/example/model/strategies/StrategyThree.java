package com.example.model.strategies;

import com.example.model.Card;

import java.io.Serializable;

public class StrategyThree extends Strategy implements Serializable {

    @Override
    public Card play() {
        Card card = null;
        if (table.isPlayerAFirst()) {
            for (Card item : hand) {
                if (card == null) {
                    card = item;
                } else if (item.getRank().getPoints() < card.getRank().getPoints()){
                    card = item;
                }
            }
            hand.remove(card);
            return card;
        } else {
            for (Card item : hand) {
                if (table.getCardB().getRank().getPoints() > 0) {
                    if (table.getCardB().getSuit().equals(item.getSuit()) &&
                            item.getRank().compareTo(table.getCardB().getRank()) > 0) {
                        if (card == null) {
                            card = item;
                        } else if (item.getRank().compareTo(card.getRank()) > 0) {
                            card = item;
                        }
                    }
                }
            }
            if (card == null) {
                for (Card item : hand) {
                    if (table.getCardB().getSuit().equals(table.getTrumpSuit())) {
                        if (card == null) {
                            card = item;
                        } else if (item.getRank().compareTo(card.getRank()) < 0) {
                            card = item;
                        }
                    }
                }
            }

            hand.remove(card);
            if (card != null) return card;
        }
        return hand.remove((int) (Math.random() * hand.size()));
    }
}
