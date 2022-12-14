package com.example.view;

import com.example.enums.Movement;
import com.example.enums.Position;
import com.example.model.Card;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Objects;

public class GraphicCardsFactory {
    private final ArrayList<GraphicCard> graphicCards = new ArrayList<>();
    private final Image cover = makeCoverImage();
    private final Pane pane;

    public GraphicCardsFactory(Pane pane) {
        this.pane = pane;
    }

    public GraphicCard getGraphicCard(Card card) {
        for (GraphicCard item : graphicCards) if (item.getCard() == card) return item;
        return newGraphicCard(card);
    }

    public Position getNewPosition(Movement movement) {
        if (movement == Movement.TO_PLAYER_A) {
            boolean a = true;
            boolean b = true;
            boolean c = true;
            for (GraphicCard item : graphicCards) {
                if (item.getPosition() == Position.PLAYER_A_CARD_1) a = false;
                else if (item.getPosition() == Position.PLAYER_A_CARD_2) b = false;
                else if (item.getPosition() == Position.PLAYER_A_CARD_3) c = false;
            }
            if (a) return Position.PLAYER_A_CARD_1;
            if (b) return Position.PLAYER_A_CARD_2;
            if (c) return Position.PLAYER_A_CARD_3;
        } else if (movement == Movement.TO_PLAYER_B) {
            boolean a = true;
            boolean b = true;
            boolean c = true;
            for (GraphicCard item : graphicCards) {
                if (item.getPosition() == Position.PLAYER_B_CARD_1) a = false;
                else if (item.getPosition() == Position.PLAYER_B_CARD_2) b = false;
                else if (item.getPosition() == Position.PLAYER_B_CARD_3) c = false;
            }
            if (a) return Position.PLAYER_B_CARD_1;
            if (b) return Position.PLAYER_B_CARD_2;
            if (c) return Position.PLAYER_B_CARD_3;
        }
        return null;
    }

    public void deleteGraphicCard(GraphicCard graphicCard) {
        graphicCards.remove(graphicCard);
    }

    private GraphicCard newGraphicCard(Card card) {
        GraphicCard graphicCard = new GraphicCard(card, makeImage(card), cover);
        graphicCard.setPosition(Position.DECK);
        graphicCard.getImageView().relocate(graphicCard.getPosition().getX(), graphicCard.getPosition().getY());
        pane.getChildren().add(graphicCard.getImageView());
        graphicCards.add(graphicCard);
        return graphicCard;
    }

    private Image makeImage(Card card) {
        String string = card.getRank().getRankChar() + card.getSuit().getSuitChar() + ".png";
        ClassLoader classLoader = getClass().getClassLoader();
        String fileURL = Objects.requireNonNull(classLoader.getResource(string)).toExternalForm();
        return new Image(fileURL);
    }

    private Image makeCoverImage() {
        ClassLoader classLoader = getClass().getClassLoader();
        String fileURL = Objects.requireNonNull(classLoader.getResource("Cover.png")).toExternalForm();
        return new Image(fileURL);
    }
}
