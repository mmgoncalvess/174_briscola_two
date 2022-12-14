package com.example.enums;

public enum Suit {
    CLUBS("C"),
    DIAMONDS("D"),
    HEARTS("H"),
    SPADES("S");

    private final String suitChar;

    Suit(String suitChar) {
        this.suitChar = suitChar;
    }

    public String getSuitChar() {
        return suitChar;
    }
}
