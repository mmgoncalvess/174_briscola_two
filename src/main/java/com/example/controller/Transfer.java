package com.example.controller;

import com.example.enums.Movement;
import com.example.model.Card;

public class Transfer {
    private final Card card;
    private final Movement movement;
    private final Scores scores;

    public Transfer(Card card, Movement movement, Scores scores) {
        this.card = card;
        this.movement = movement;
        this.scores = scores;
    }

    public Card getCard() {
        return card;
    }

    public Movement getMovement() {
        return movement;
    }

    public Scores getScores() {
        return scores;
    }

    @Override
    public String toString() {
        return "Card:" + card + "  Movement:" + movement + "  Scores:" + scores;
    }
}
