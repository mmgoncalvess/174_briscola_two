package com.example.view;

import com.example.controller.Controller;
import com.example.controller.Scores;
import com.example.controller.Transfer;
import com.example.enums.Position;
import com.example.enums.Suit;
import com.example.model.Card;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class Animation {
    private final Text labelDeck;
    private final Text labelPlayerA ;
    private final Text labelPlayerB;
    private final Pane pane;
    private final Stage stage;
    private final Controller controller;
    private final Duration duration = Duration.millis(400);
    private GraphicCardsFactory graphicCardsFactory;
    private boolean pause = true;
    private boolean allowMouse;
    private Card trump;

    public Animation(Pane pane, Text labelDeck, Text labelPlayerA, Text labelPlayerB, Stage stage, Controller controller) {
        this.pane = pane;
        this.labelDeck = labelDeck;
        this.labelPlayerA = labelPlayerA;
        this.labelPlayerB = labelPlayerB;
        this.stage = stage;
        this.controller = controller;
    }

    public void init() {
        graphicCardsFactory = new GraphicCardsFactory(pane);
        executeNextTransition(getNextTransfer());
    }

    public boolean isAllowMouse() {
        return allowMouse;
    }

    public void setAllowMouse(boolean allowMouse) {
        this.allowMouse = allowMouse;
    }

    private void executeNextTransition(Transfer transfer) {
        if (transfer == null) {
            allowMouse = true;
            return;
        }
        switch (transfer.getMovement()) {
            case TO_PLAYER_A: {
                GraphicCard graphicCard = graphicCardsFactory.getGraphicCard(transfer.getCard());
                if (transfer.getCard() == trump) {
                    graphicCard.setCovered(true);
                    graphicCard.getImageView().setRotate(0);
                }
                Position newPosition = graphicCardsFactory.getNewPosition(transfer.getMovement());
                TranslateTransition transition = getTransition(newPosition, graphicCard);
                transition.setOnFinished(event -> executeNextTransition(getNextTransfer()));
                transition.play();
                graphicCard.setPosition(newPosition);
                updateLabels(transfer.getScores());
                break;
            }

            case TO_PLAYER_B: {
                GraphicCard graphicCard = graphicCardsFactory.getGraphicCard(transfer.getCard());
                if (transfer.getCard() == trump) graphicCard.getImageView().setRotate(0);
                graphicCard.setCovered(false);
                Position newPosition = graphicCardsFactory.getNewPosition(transfer.getMovement());
                TranslateTransition transition = getTransition(newPosition, graphicCard);
                transition.setOnFinished(event -> {
                    graphicCard.getImageView().setOnMouseClicked(event1 -> {
                        if (allowMouse) {
                            graphicCard.getImageView().setOnMouseClicked(null);
                            allowMouse = false;
                            controller.playerBPlay(graphicCard.getCard());
                            executeNextTransition(getNextTransfer());
                        }
                    });
                    executeNextTransition(getNextTransfer());
                });
                transition.play();
                graphicCard.setPosition(newPosition);
                updateLabels(transfer.getScores());
                break;
            }

            case TO_TABLE_A: {
                GraphicCard graphicCard = graphicCardsFactory.getGraphicCard(transfer.getCard());
                graphicCard.setCovered(false);
                Position newPosition = Position.TABLE_1;
                TranslateTransition transition = getTransition(newPosition, graphicCard);
                transition.setOnFinished(event -> executeNextTransition(getNextTransfer()));
                transition.play();
                graphicCard.setPosition(newPosition);
                break;
            }

            case TO_TABLE_B: {
                GraphicCard graphicCard = graphicCardsFactory.getGraphicCard(transfer.getCard());
                Position newPosition = Position.TABLE_2;
                TranslateTransition transition = getTransition(newPosition, graphicCard);
                transition.setOnFinished(event -> executeNextTransition(getNextTransfer()));
                transition.play();
                graphicCard.setPosition(newPosition);
                break;
            }
            case TO_PILE_A: {
                GraphicCard graphicCard = graphicCardsFactory.getGraphicCard(transfer.getCard());
                Position newPosition = Position.PLAYER_A_PILE;
                TranslateTransition transition = getTransition(newPosition, graphicCard);
                transition.setOnFinished(event -> {
                    graphicCard.setCovered(true);
                    updateLabels(transfer.getScores());
                    graphicCardsFactory.deleteGraphicCard(graphicCard);
                    executeNextTransition(getNextTransfer());
                });
                pause();
                transition.play();
                break;
            }
            case TO_PILE_B: {
                GraphicCard graphicCard = graphicCardsFactory.getGraphicCard(transfer.getCard());
                Position newPosition = Position.PLAYER_B_PILE;
                TranslateTransition transition = getTransition(newPosition, graphicCard);
                transition.setOnFinished(event -> {
                    graphicCard.setCovered(true);
                    updateLabels(transfer.getScores());
                    graphicCardsFactory.deleteGraphicCard(graphicCard);
                    executeNextTransition(getNextTransfer());
                });
                pause();
                transition.play();
                break;
            }
            case TO_TRUMP:{
                GraphicCard graphicCard = graphicCardsFactory.getGraphicCard(transfer.getCard());
                trump = transfer.getCard();
                graphicCard.getImageView().toBack();
                graphicCard.getImageView().setRotate(90);
                graphicCard.setCovered(false);
                Position newPosition = Position.TRUMP;
                TranslateTransition transition = getTransition(newPosition, graphicCard);
                transition.setOnFinished(event -> {
                    allowMouse = true;
                    stage.getIcons().clear();
                    stage.getIcons().add(getIcon(trump.getSuit()));
                });
                transition.play();
                graphicCard.setPosition(newPosition);
                updateLabels(transfer.getScores());
                break;
            }
            case TO_DECK:{
                GraphicCard graphicCard = graphicCardsFactory.getGraphicCard(transfer.getCard());
                graphicCard.getImageView().setOnMouseClicked(event -> {
                    if (allowMouse) {
                        graphicCard.getImageView().setOnMouseClicked(null);
                        allowMouse = false;
                        executeNextTransition(getNextTransfer());
                    }
                });
                updateLabels(transfer.getScores());
                executeNextTransition(getNextTransfer());
                break;
            }
        }
    }

    private Transfer getNextTransfer() {
        return controller.getTransfer();
    }

    private void updateLabels(Scores scores) {
        labelDeck.setText(String.valueOf(scores.getCardsInDeck()));
        labelPlayerA.setText(String.valueOf(scores.getPointsPlayerA()));
        labelPlayerB.setText(String.valueOf(scores.getPointsPlayerB()));
    }

    private TranslateTransition getTransition(Position newPosition, GraphicCard graphicCard) {
        int deltaX = newPosition.getX() - graphicCard.getPosition().getX();
        int deltaY = newPosition.getY() - graphicCard.getPosition().getY();
        TranslateTransition transition = new TranslateTransition(duration, graphicCard.getImageView());
        transition.setByX(deltaX);
        transition.setByY(deltaY);
        return transition;
    }

    private void pause() {
        if (pause) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pause = !pause;
    }

    public Image getIcon(Suit suit) {
        String string = null;
        switch (suit) {
            case CLUBS:
                string = "clubs.jpg";
                break;
            case SPADES:
                string = "spades.png";
                break;
            case HEARTS:
                string = "hearts.png";
                break;
            case DIAMONDS:
                string = "diamonds.png";
                break;
        }
        ClassLoader classLoader = getClass().getClassLoader();
        String fileURL = Objects.requireNonNull(classLoader.getResource(string)).toExternalForm();
        return new Image(fileURL);
    }
}
