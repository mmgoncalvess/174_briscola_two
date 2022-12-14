package com.example.model.strategies;

import com.example.model.Card;

import java.io.Serializable;

public class StrategyTwo extends Strategy implements Serializable {
    @Override
    public Card play() {
        return hand.remove((int) (Math.random() * hand.size()));
    }
}
