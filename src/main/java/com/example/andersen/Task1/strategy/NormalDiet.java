package com.example.andersen.Task1.strategy;

public class NormalDiet implements EatBehavior {

    @Override
    public void eat() {
        System.out.println("Typically cat food");
    }
}