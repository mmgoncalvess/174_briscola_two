package com.example.model;

import com.example.enums.Rank;
import com.example.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards;
    private final Card trump;
    private final Card beforeLast;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        trump = cards.get(0);
        beforeLast = cards.get(1);
    }

    public Card getNextCard() {
        if (cards.size() == 0) return null;
        return cards.remove(cards.size() - 1);
    }

    public Card getTrump() {
        return trump;
    }

    public Card getBeforeLast() {
        return beforeLast;
    }

    private void initializeDeck() {
        for (Rank rank: Rank.values()) {
            for (Suit suit: Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public int getSize() {
        return cards.size();
    }

    @Override
    public String toString() {
        return "Size:" + cards.size() + "  Trump: " + trump;
    }
}
