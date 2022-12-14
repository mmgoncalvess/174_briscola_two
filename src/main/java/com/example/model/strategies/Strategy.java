package com.example.model.strategies;

import com.example.model.Card;
import com.example.model.Table;

import java.util.ArrayList;

public abstract class Strategy {
    protected ArrayList<Card> hand;
    protected Table table;

    public void setHand(ArrayList<Card> arrayList) {
        this.hand = arrayList;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public abstract Card play();

}
