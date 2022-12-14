package com.example.model;

import com.example.controller.Scores;
import com.example.controller.Transfer;
import com.example.enums.Movement;
import com.example.model.strategies.Strategy;

public class Game {
    private final Deck deck = new Deck();

    private final Player playerB = new Player("B");
    private final Table table = new Table(deck.getTrump().getSuit());
    private final AutomaticPlayer playerA = new AutomaticPlayer("A", table);
    private final Transfers transfers = new Transfers();
    private boolean isPlayerAFirst;

    public void start() {
        getFirstPlayer();
        sendDeckAndTrump();
        initialDeal();
        printHands();
        if(isPlayerAFirst) playerAPlay();
    }

    public void playerBPlay(Card card) {
        if (card == null) return;
        Card cardReceived = playerB.play(card);
        if (cardReceived == null) return;
        table.setCardPlayerB(cardReceived);
        Scores scores = new Scores(playerA.getPoints(), playerB.getPoints(), deck.getSize());
        transfers.addTransfer(new Transfer(cardReceived, Movement.TO_TABLE_B, scores));
        runCycle();
    }

    public void setStrategy(Strategy strategy) {
        playerA.setStrategy(strategy);
    }

    public Transfer getTransfer() {
        return transfers.getTransfer();
    }

    private void getFirstPlayer() {
        isPlayerAFirst = (Math.random() > 0.5);
        table.setPlayerAFirst(isPlayerAFirst);
    }

    private void sendDeckAndTrump() {
        Scores scores = new Scores(0, 0, deck.getSize());
        transfers.addTransfer(new Transfer(deck.getBeforeLast(), Movement.TO_DECK, scores));
        transfers.addTransfer(new Transfer(deck.getTrump(), Movement.TO_TRUMP, scores));
    }

    private void initialDeal() {
        Player firstPlayer = isPlayerAFirst? playerA : playerB;
        Player secondPlayer = isPlayerAFirst? playerB : playerA;
        for (int i = 0; i < 3; i++) {
            Card card = deck.getNextCard();
            Movement movement = isPlayerAFirst? Movement.TO_PLAYER_A : Movement.TO_PLAYER_B;
            firstPlayer.deal(card);
            Scores scores = new Scores(playerA.getPoints(), playerB.getPoints(), deck.getSize());
            transfers.addTransfer(new Transfer(card, movement, scores));
        }
        for (int i = 0; i < 3; i++) {
            Card card = deck.getNextCard();
            Movement movement = isPlayerAFirst? Movement.TO_PLAYER_B : Movement.TO_PLAYER_A;
            secondPlayer.deal(card);
            Scores scores = new Scores(playerA.getPoints(), playerB.getPoints(), deck.getSize());
            transfers.addTransfer(new Transfer(card, movement, scores));
        }
    }

    private void runCycle() {
        if(!isPlayerAFirst) playerAPlay();
        printCardsPlayed();
        decideWinner();
        addPoints();
        printPoints();
        dealCards();
        printHands();
        if (playerA.hand.size() == 0) return;
        if(isPlayerAFirst) playerAPlay();
    }

    private void playerAPlay() {
        Scores scores = new Scores(playerA.getPoints(), playerB.getPoints(), deck.getSize());
        Card card = playerA.play();
        table.setCardPlayerA(card);
        transfers.addTransfer(new Transfer(card, Movement.TO_TABLE_A, scores));
    }

    private void decideWinner() {
        isPlayerAFirst = table.isPlayerAWinner();
    }

    private void addPoints() {
        Player player = isPlayerAFirst ? playerA : playerB;
        Card cardOne = table.removeCardPlayerA();
        Card cardTwo = table.removeCardPlayerB();
        Movement movement = isPlayerAFirst? Movement.TO_PILE_A : Movement.TO_PILE_B;
        player.addPoints(cardOne);
        Scores scores = new Scores(playerA.getPoints(), playerB.getPoints(), deck.getSize());
        transfers.addTransfer(new Transfer(cardOne, movement, scores));
        player.addPoints(cardTwo);
        scores = new Scores(playerA.getPoints(), playerB.getPoints(), deck.getSize());
        transfers.addTransfer(new Transfer(cardTwo, movement, scores));
    }

    private void dealCards() {
        if (deck.getSize() == 0) return;
        Player firstPlayer = isPlayerAFirst? playerA : playerB;
        Card firstCard = deck.getNextCard();
        Movement firstMovement = isPlayerAFirst? Movement.TO_PLAYER_A : Movement.TO_PLAYER_B;
        firstPlayer.deal(firstCard);
        Scores scores = new Scores(playerA.getPoints(), playerB.getPoints(), deck.getSize());
        transfers.addTransfer(new Transfer(firstCard, firstMovement, scores));

        Player secondPlayer = isPlayerAFirst? playerB : playerA;
        Card secondCard = deck.getNextCard();
        Movement secondMovement = isPlayerAFirst? Movement.TO_PLAYER_B : Movement.TO_PLAYER_A;
        secondPlayer.deal(secondCard);
        scores = new Scores(playerA.getPoints(), playerB.getPoints(), deck.getSize());
        transfers.addTransfer(new Transfer(secondCard, secondMovement, scores));
    }

    private void printHands() {
        System.out.println("Hands:  " + playerA + "    " + playerB);
    }

    private void printCardsPlayed() {
        System.out.println(table);
    }

    private void printPoints() {
        String string = String.format("Points: Player %1s:  %3d  Player %1s:  %3d  Total:  %3d\n",
                isPlayerAFirst? "A" : "B",
                isPlayerAFirst? playerA.getPoints() : playerB.getPoints(),
                isPlayerAFirst? "B" : "A",
                isPlayerAFirst? playerB.getPoints() : playerA.getPoints(),
                playerA.getPoints() + playerB.getPoints()
        );
        System.out.println(string);
    }
}
