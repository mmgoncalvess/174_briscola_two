package com.example.view;

import com.example.enums.Position;
import com.example.model.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GraphicCard {
    private final Card card;
    private final Image image;
    private final Image cover;
    private final ImageView imageView = new ImageView();
    private Position position;

    public GraphicCard(Card card, Image image, Image cover) {
        this.card = card;
        this.image = image;
        this.cover = cover;
        setCovered(true);
    }

    public void setCovered(boolean bool) {
        if (bool) {
            imageView.setImage(cover);
        } else {
            imageView.setImage(image);
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Card getCard() {
        return card;
    }
}
