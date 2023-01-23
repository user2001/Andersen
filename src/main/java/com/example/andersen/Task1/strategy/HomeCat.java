package com.example.andersen.Task1.strategy;

public class HomeCat extends Cat {
    public HomeCat() {
        soundBehaviour = new RelaxCatSound();
        eatBehavior = new NormalDiet();
    }

    @Override
    public void display() {
        System.out.println("I am nice home cat");
    }
}
