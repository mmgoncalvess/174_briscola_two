package com.example.model;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int points;
    protected final ArrayList<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void deal(Card card) {
        if (hand.size() < 3 && card != null) {
            hand.add(card);
        }
    }

    public Card play(Card card) {
        return hand.remove(card)? card : null;
    }

    public void addPoints(Card card) {
        points += card.getRank().getPoints();
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("Player %1s:  %2s  %2s  %2s",
            name,
            hand.size()>0 ? hand.get(0) : "--",
            hand.size()>1 ? hand.get(1) : "--",
            hand.size()>2 ? hand.get(2) : "--");
    }
}
