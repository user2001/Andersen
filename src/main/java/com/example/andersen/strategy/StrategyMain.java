package com.example.andersen.strategy;

public class StrategyMain {
    public static void main(String[] args) {
        Cat homeCat = new HomeCat();
        homeCat.doSound();
        homeCat.doEat();
    }
}
