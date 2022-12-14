package com.example.controller;

import com.example.model.Card;
import com.example.model.Coupling;
import com.example.model.strategies.Strategy;

public class Controller {
    private final Coupling coupling = new Coupling();

    public void newGame() {
        coupling.newGame();
        coupling.init();
    }

    public void playerBPlay(Card card) {
        coupling.playerBPlay(card);
    }

    public void setStrategy(Strategy strategy) {
        coupling.setStrategy(strategy);
    }

    public Transfer getTransfer() {
        return coupling.getTransfer();
    }

}
