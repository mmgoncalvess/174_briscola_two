package com.example.enums;

public enum Rank {
    TWO("2", 0),
    THREE("3", 0),
    FOUR("4", 0),
    FIVE("5", 0),
    SIX("6", 0),
    QUEEN("Q", 2),
    JACK("J", 3),
    KING("K", 4),
    SEVEN("7", 10),
    ACE("A", 11);

    private final String rankChar;
    private final int points;

    Rank(String rankChar, int points) {
        this.rankChar = rankChar;
        this.points = points;
    }

    public String getRankChar() {
        return rankChar;
    }

    public int getPoints() {return points;}
}
