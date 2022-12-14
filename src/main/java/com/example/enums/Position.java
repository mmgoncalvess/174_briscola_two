package com.example.enums;

public enum Position {
    PLAYER_A_CARD_1(465,60),
    PLAYER_A_CARD_2(641,60),
    PLAYER_A_CARD_3(818,60),
    PLAYER_A_PILE(1223,170),
    PLAYER_B_CARD_1(465,626),
    PLAYER_B_CARD_2(641,626),
    PLAYER_B_CARD_3(818,626),
    PLAYER_B_PILE(1223,516),
    DECK(60,343),
    TRUMP(95,343),
    TABLE_1(563,343),
    TABLE_2(730,343);

    private final int pixelX;
    private final int pixelY;

    Position(int pixelX, int pixelY) {
        this.pixelX = pixelX;
        this.pixelY = pixelY;
    }

    public int getX() {
        return pixelX;
    }

    public int getY() {
        return pixelY;
    }

    @Override
    public String toString() {
        return "Position{" +
                "pixelX=" + pixelX +
                ", pixelY=" + pixelY +
                '}';
    }
}
