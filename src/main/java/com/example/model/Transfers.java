package com.example.model;

import com.example.controller.Transfer;

import java.util.LinkedList;
import java.util.Queue;

public class Transfers {
    private final Queue<Transfer> queue = new LinkedList<>();

    public void addTransfer(Transfer transfer) {
        queue.add(transfer);
    }

    public Transfer getTransfer() {
        return queue.poll();
    }
}
