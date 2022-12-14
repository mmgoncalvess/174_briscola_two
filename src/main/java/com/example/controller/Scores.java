package com.example.controller;
public class Scores {
    private final int pointsPlayerA;
    private final int pointsPlayerB;
    private final int cardsInDeck;

    public Scores(int pointsPlayerA, int pointsPlayerB, int cardsInDeck) {
        this.pointsPlayerA = pointsPlayerA;
        this.pointsPlayerB = pointsPlayerB;
        this.cardsInDeck = cardsInDeck;
    }

    public int getPointsPlayerA() {
        return pointsPlayerA;
    }

    public int getPointsPlayerB() {
        return pointsPlayerB;
    }

    public int getCardsInDeck() {
        return cardsInDeck;
    }

    @Override
    public String toString() {
        return "Scores: A:" + pointsPlayerA + "  B:" + pointsPlayerB + "  Deck:" + cardsInDeck;
    }
}
