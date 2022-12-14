package com.example.view;

import com.example.controller.Controller;
import com.example.model.strategies.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class App extends Application {
    private ColorPicker colorPicker;
    private Button button;
    private VBox vBox;
    private Controller controller;
    private Text labelDeck;
    private Text labelPlayerA;
    private Text labelPlayerB;
    private Animation animation;

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1400, 865);
        stage.setTitle("Briscola");
        stage.setScene(scene);
        stage.show();
        controller = new Controller();
        controller.newGame();
        initNodes(pane);
        addNodes(pane);
        animation = new Animation(pane, labelDeck, labelPlayerA, labelPlayerB, stage, controller);
        animation.init();
    }

    private void initNodes(Pane pane) {
        RadioButton levelOne = new RadioButton("Level 1");
        RadioButton levelTwo = new RadioButton("Level 2");
        RadioButton levelThree = new RadioButton("Level 3");
        RadioButton levelFour = new RadioButton("Level 4");
        ToggleGroup toggleGroup = new ToggleGroup();
        levelOne.setToggleGroup(toggleGroup);
        levelTwo.setToggleGroup(toggleGroup);
        levelThree.setToggleGroup(toggleGroup);
        levelFour.setToggleGroup(toggleGroup);
        Strategy strategy = readStrategy();

        if (strategy instanceof StrategyOne) {
            toggleGroup.selectToggle(levelOne);
        } else if (strategy instanceof StrategyTwo) {
            toggleGroup.selectToggle(levelTwo);
        } else if (strategy instanceof StrategyThree) {
            toggleGroup.selectToggle(levelThree);
        }else if (strategy instanceof StrategyFour) {
            toggleGroup.selectToggle(levelFour);
        }

        levelOne.setOnAction(event -> controller.setStrategy(new StrategyOne()));
        levelTwo.setOnAction(event -> controller.setStrategy(new StrategyTwo()));
        levelThree.setOnAction(event -> controller.setStrategy(new StrategyThree()));
        levelFour.setOnAction(event -> controller.setStrategy(new StrategyFour()));
        vBox = new VBox(levelOne, levelTwo, levelThree, levelFour);

        labelDeck = new Text("DECK");
        labelPlayerA = new Text("POINTS");
        labelPlayerB = new Text("POINTS");
        Font font = new Font("Arial", 25);
        labelDeck.setFont(font);
        labelPlayerA.setFont(font);
        labelPlayerB.setFont(font);
        labelDeck.setTextAlignment(TextAlignment.CENTER);
        labelPlayerA.setTextAlignment(TextAlignment.CENTER);
        labelPlayerB.setTextAlignment(TextAlignment.CENTER);
        labelDeck.setWrappingWidth(100);
        labelPlayerA.setWrappingWidth(100);
        labelPlayerB.setWrappingWidth(100);
        labelDeck.setLayoutX(69);
        labelDeck.setLayoutY(328);
        labelPlayerA.setLayoutX(1230);
        labelPlayerA.setLayoutY(380);
        labelPlayerB.setLayoutX(1230);
        labelPlayerB.setLayoutY(500);

        colorPicker = new ColorPicker();
        Color color = getRandomColor();
        colorPicker.setOnAction(event -> setBackground(pane, colorPicker.getValue()));
        colorPicker.getStyleClass().add("button");
        colorPicker.setStyle("-fx-color-label-visible: false; -fx-color-rect-height: 27; -fx-color-rect-width: 87;");
        colorPicker.setPrefWidth(90);
        setBackground(pane, color);

        button = new Button("New game");
        button.setOnAction(event -> {
            if (animation.isAllowMouse()) {
                animation.setAllowMouse(false);
                pane.getChildren().clear();
                addNodes(pane);
                setBackground(pane, getRandomColor());
                controller.newGame();
                animation.init();
            }
        });
    }

    private void addNodes(Pane pane) {
        pane.getChildren().addAll(labelDeck, labelPlayerA, labelPlayerB);
        pane.getChildren().add(colorPicker);
        colorPicker.relocate(1236, 60);
        pane.getChildren().add(button);
        button.relocate(60, 60);
        pane.getChildren().add(vBox);
        vBox.relocate(60, 110);
    }

    private Color getRandomColor() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }

    private void setBackground(Pane pane, Color color) {
        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        colorPicker.setValue(color);
    }

    private Strategy readStrategy() {
        Strategy strategy;
        File file = new File("strategy");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileInputStream);
            Object obj = objectIn.readObject();
            System.out.println("The Object has been read from the file");
            objectIn.close();
            strategy = (Strategy) obj;
            return strategy;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return new StrategyOne();
    }
}
