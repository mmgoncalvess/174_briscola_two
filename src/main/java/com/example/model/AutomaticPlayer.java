package com.example.model;

import com.example.model.strategies.Strategy;
import com.example.model.strategies.StrategyOne;

import java.io.*;

public class AutomaticPlayer extends Player {
    private Strategy strategy;
    private Table table;

    public AutomaticPlayer(String name, Table table) {
        super(name);
        this.table = table;
        readStrategy();
    }

    public Card play() {
        return strategy.play();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
        this.strategy.setHand(hand);
        this.strategy.setTable(table);
        File file = new File("strategy");
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(strategy);
            objectOut.close();
            System.out.println("The strategy  was successfully written to a file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readStrategy() {
        File file = new File("strategy");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileInputStream);
            Object obj = objectIn.readObject();
            System.out.println("The Object has been read from the file");
            objectIn.close();
            strategy = (Strategy) obj;
            strategy.setHand(hand);
            strategy.setTable(table);
            return;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        strategy = new StrategyOne();
        strategy.setHand(hand);
        strategy.setTable(table);
    }
}
