package com.example.model;

import com.example.controller.Transfer;
import com.example.model.strategies.Strategy;

public class Coupling {
    private Game game;

    public synchronized void newGame() {
        game = null;
        game = new Game();
        System.out.println("Game: " + game);
    }

    public synchronized void init() {
        game.start();
    }

    public synchronized void playerBPlay(Card card) {
        game.playerBPlay(card);
    }

    public synchronized void setStrategy(Strategy strategy) {
        game.setStrategy(strategy);
    }

    public synchronized Transfer getTransfer() {
        return game.getTransfer();
    }
}
