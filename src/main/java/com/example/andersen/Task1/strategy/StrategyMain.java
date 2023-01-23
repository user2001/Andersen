package com.example.andersen.Task1.strategy;

public class StrategyMain {
    public static void main(String[] args) {
        Cat homeCat = new HomeCat();
        homeCat.doSound();
        homeCat.doEat();
    }
}
