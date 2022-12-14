package com.example.model.strategies;

import com.example.model.Card;

import java.io.Serializable;

public class StrategyFour extends Strategy implements Serializable {

    @Override
    public Card play() {
        Card card = null;
        if (table.isPlayerAFirst() || table.getCardB().getRank().getPoints() == 0) {
            for (Card item : hand) {
                if (card == null) {
                    card = item;
                } else if (item.getRank().getPoints() < card.getRank().getPoints()){
                    card = item;
                }
            }
            hand.remove(card);
            return card;
        } else if (table.getCardB().getRank().getPoints() < 4) {
            for (Card item : hand) {
                if (table.getCardB().getSuit() == item.getSuit() && !table.getCardB().getSuit().equals(table.getTrumpSuit())) {
                    if (card == null || item.getRank().compareTo(table.getCardB().getRank()) > 0) {
                        card = item;
                    } else if (item.getRank().compareTo(card.getRank()) > 0) {
                        card = item;
                    }
                }
            }
        } else if (table.getCardB().getRank().getPoints() > 3) {
            for (Card item : hand) {
                if (item.getSuit().equals(table.getCardB().getSuit()) &&
                        item.getRank().compareTo(table.getCardB().getRank()) > 0) {
                    card = item;
                }
            }
            if (card == null && !table.getCardB().getSuit().equals(table.getTrumpSuit())) {
                for (Card item : hand) {
                    if (item.getSuit().equals(table.getTrumpSuit())) {
                        if (card == null) {
                            card = item;
                        } else if (item.getRank().compareTo(card.getRank()) < 0) {
                            card = item;
                        }
                    }
                }
            }
        }
        hand.remove(card);
        if (card != null) return card;
        return hand.remove((int) (Math.random() * hand.size()));
    }
}
