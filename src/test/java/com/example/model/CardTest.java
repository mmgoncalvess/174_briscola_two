package com.example.model;

import com.example.enums.Rank;
import com.example.enums.Suit;
import com.example.model.Card;
import org.junit.jupiter.api.Assertions;

class CardTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getRank() {
        Card card = new Card(Rank.QUEEN, Suit.DIAMONDS);
        Rank actualValue = card.getRank();
        Rank expectedValue = Rank.QUEEN;
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    void getSuit() {
        Card card = new Card(Rank.QUEEN, Suit.SPADES);
        Suit actualValue = card.getSuit();
        Suit expectedValue = card.getSuit();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Card card = new Card(Rank.JACK, Suit.SPADES);
        String actualValue = card.toString();
        String expectedValue = "JS";
        Assertions.assertEquals(expectedValue, actualValue);
    }
}