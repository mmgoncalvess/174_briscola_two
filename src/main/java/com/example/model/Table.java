package com.example.model;

import com.example.enums.Suit;

public class Table {
    private Card cardPlayerA;
    private Card cardPlayerB;
    private final Suit trumpSuit;
    private boolean isPlayerAFirst;

    public Table(Suit trump) {
        this.trumpSuit = trump;
    }

    public boolean isPlayerAWinner() {
        boolean result = isPlayerAFirst;
        if(cardPlayerA.getSuit().equals(cardPlayerB.getSuit())) {
            result = cardPlayerA.getRank().compareTo(cardPlayerB.getRank()) > 0;
        } else if(cardPlayerA.getSuit().equals(trumpSuit) || cardPlayerB.getSuit().equals(trumpSuit)) {
            result = cardPlayerA.getSuit().equals(trumpSuit);
        }
        isPlayerAFirst = result;
        return result;
    }

    public Card removeCardPlayerA() {
        Card card = cardPlayerA;
        cardPlayerA = null;
        return card;
    }

    public Card removeCardPlayerB() {
        Card card = cardPlayerB;
        cardPlayerB = null;
        return card;
    }

    public void setCardPlayerA(Card cardPlayerA) {
        this.cardPlayerA = cardPlayerA;
    }

    public void setCardPlayerB(Card cardPlayerB) {
        this.cardPlayerB = cardPlayerB;
    }

    public void setPlayerAFirst(boolean playerAFirst) {
        isPlayerAFirst = playerAFirst;
    }

    public Suit getTrumpSuit() {
        return trumpSuit;
    }

    public Card getCardB() {
        return cardPlayerB;
    }

    public boolean isPlayerAFirst() {
        return isPlayerAFirst;
    }


    @Override
    public String toString() {
        return String.format("Played: Player %1s:  %2s  Player %1s:  %2s  Trump:  %1s",
                isPlayerAFirst? "A" : "B",
                isPlayerAFirst? cardPlayerA : cardPlayerB,
                isPlayerAFirst? "B" : "A",
                isPlayerAFirst? cardPlayerB : cardPlayerA,
                trumpSuit.getSuitChar()
                );
    }
}
